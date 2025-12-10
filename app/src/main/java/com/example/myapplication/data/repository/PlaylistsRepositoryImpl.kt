package com.example.myapplication.data.repository

import com.example.myapplication.data.db.DbProvider
import com.example.myapplication.domain.api.PlaylistsRepository
import com.example.myapplication.domain.models.Playlist
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import java.util.Calendar

class PlaylistsRepositoryImpl(
    private val scope: CoroutineScope
) : PlaylistsRepository {
    private val database = DbProvider.database
    override fun getPlaylist(playlistId: Long): Flow<Playlist?> {
        return database.getPlaylist(playlistId)
    }
    override fun getAllPlaylists(): Flow<List<Playlist>> {
        return database.getAllPlaylists()
    }
    override suspend fun addNewPlaylist(name: String, description: String) {
        val year = Calendar.getInstance().get(Calendar.YEAR)
        database.addNewPlaylist(
            name = name,
            description = description,
            creationYear = year
        )
    }
    override suspend fun deletePlaylistById(id: Long) {
        database.deletePlaylistById(playlistId = id)
    }
}

