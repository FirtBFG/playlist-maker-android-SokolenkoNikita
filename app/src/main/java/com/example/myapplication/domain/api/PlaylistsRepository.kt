package com.example.myapplication.domain.api

import com.example.myapplication.domain.models.Playlist
import kotlinx.coroutines.flow.Flow

interface PlaylistsRepository {
    fun getPlaylist(playlistId: Long): Flow<Playlist?>
    
    fun getAllPlaylists(): Flow<List<Playlist>>
    
    suspend fun addNewPlaylist(name: String, description: String, coverImageUri: String? = null)
    
    suspend fun deletePlaylistById(id: Long)
    
    suspend fun mergePlaylists(sourcePlaylistId: Long, targetPlaylistId: Long)
}

