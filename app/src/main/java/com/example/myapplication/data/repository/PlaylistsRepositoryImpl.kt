package com.example.myapplication.data.repository

import com.example.myapplication.data.db.DbProvider
import com.example.myapplication.data.database.PlaylistEntity
import com.example.myapplication.domain.api.PlaylistsRepository
import com.example.myapplication.domain.models.Playlist
import com.example.myapplication.domain.models.Track
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.Calendar

class PlaylistsRepositoryImpl(
    private val scope: CoroutineScope
) : PlaylistsRepository {
    private val database = DbProvider.database
    private val playlistDao = database.playlistDao()
    private val trackDao = database.trackDao()

    override fun getPlaylist(playlistId: Long): Flow<Playlist?> {
        return combine(
            playlistDao.getPlaylistById(playlistId),
            trackDao.getTracksByPlaylistId(playlistId)
        ) { playlistEntity, trackEntities ->
            playlistEntity?.let { entity ->
                Playlist(
                    id = entity.id,
                    name = entity.name,
                    description = entity.description,
                    creationYear = entity.creationYear,
                    tracks = trackEntities.map { it.toTrack() }
                )
            }
        }
    }

    override fun getAllPlaylists(): Flow<List<Playlist>> {
        return combine(
            playlistDao.getAllPlaylists(),
            trackDao.getAllTracks()
        ) { playlists, allTracks ->
            playlists.map { playlistEntity ->
                val playlistTracks = allTracks
                    .filter { it.playlistId == playlistEntity.id }
                    .map { it.toTrack() }
                Playlist(
                    id = playlistEntity.id,
                    name = playlistEntity.name,
                    description = playlistEntity.description,
                    creationYear = playlistEntity.creationYear,
                    tracks = playlistTracks
                )
            }
        }
    }

    override suspend fun addNewPlaylist(name: String, description: String) {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        playlistDao.insertPlaylist(
            PlaylistEntity(
                name = name,
                description = description,
                creationYear = currentYear
            )
        )
    }

    override suspend fun deletePlaylistById(id: Long) {
        trackDao.clearTracksByPlaylistId(id)
        playlistDao.deletePlaylistById(id)
    }
}

private fun com.example.myapplication.data.database.TrackEntity.toTrack(): Track {
    return Track(
        id = id,
        trackName = trackName,
        artistName = artistName,
        trackTime = trackTime,
        artworkUrl100 = artworkUrl100,
        collectionName = collectionName,
        releaseDate = releaseDate,
        primaryGenreName = primaryGenreName,
        country = country,
        previewUrl = previewUrl,
        favorite = favorite,
        playlistId = playlistId
    )
}
