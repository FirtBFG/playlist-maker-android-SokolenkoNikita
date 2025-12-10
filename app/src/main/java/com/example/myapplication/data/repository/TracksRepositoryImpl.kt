package com.example.myapplication.data.repository

import com.example.myapplication.data.db.DbProvider
import com.example.myapplication.data.database.TrackEntity
import com.example.myapplication.domain.api.TracksRepository
import com.example.myapplication.domain.models.Track
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class TracksRepositoryImpl(
    private val scope: CoroutineScope
) : TracksRepository {
    private val database = DbProvider.database
    private val trackDao = database.trackDao()

    override suspend fun searchTracks(expression: String): List<Track> {
        return emptyList()
    }

    override fun getTrackByNameAndArtist(track: Track): Flow<Track?> {
        return trackDao.getTrackByNameAndArtist(track.trackName, track.artistName)
            .map { it?.toTrack() }
    }

    override suspend fun insertTrackToPlaylist(track: Track, playlistId: Long) {
        val existingTrack = trackDao.getTrackByNameAndArtist(track.trackName, track.artistName).first()
        val trackEntity = if (existingTrack != null) {
            existingTrack.copy(playlistId = playlistId, favorite = track.favorite)
        } else {
            TrackEntity(
                trackName = track.trackName,
                artistName = track.artistName,
                trackTime = track.trackTime,
                artworkUrl100 = track.artworkUrl100,
                collectionName = track.collectionName,
                releaseDate = track.releaseDate,
                primaryGenreName = track.primaryGenreName,
                country = track.country,
                previewUrl = track.previewUrl,
                favorite = track.favorite,
                playlistId = playlistId
            )
        }
        trackDao.insertTrack(trackEntity)
    }

    override suspend fun deleteTrackFromPlaylist(track: Track) {
        val existingTrack = trackDao.getTrackByNameAndArtist(track.trackName, track.artistName).first()
        if (existingTrack != null) {
            trackDao.insertTrack(existingTrack.copy(playlistId = 0))
        }
    }

    override suspend fun updateTrackFavoriteStatus(track: Track, isFavorite: Boolean) {
        val existingTrack = trackDao.getTrackByNameAndArtist(track.trackName, track.artistName).first()
        val trackEntity = if (existingTrack != null) {
            existingTrack.copy(favorite = isFavorite)
        } else {
            TrackEntity(
                trackName = track.trackName,
                artistName = track.artistName,
                trackTime = track.trackTime,
                artworkUrl100 = track.artworkUrl100,
                collectionName = track.collectionName,
                releaseDate = track.releaseDate,
                primaryGenreName = track.primaryGenreName,
                country = track.country,
                previewUrl = track.previewUrl,
                favorite = isFavorite,
                playlistId = track.playlistId
            )
        }
        trackDao.insertTrack(trackEntity)
    }

    override fun deleteTracksByPlaylistId(playlistId: Long) {
        // Not used directly - handled in PlaylistsRepositoryImpl
    }

    override fun getFavoriteTracks(): Flow<List<Track>> {
        return trackDao.getFavoriteTracks().map { tracks ->
            tracks.map { it.toTrack() }
        }
    }
}

private fun TrackEntity.toTrack(): Track {
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
