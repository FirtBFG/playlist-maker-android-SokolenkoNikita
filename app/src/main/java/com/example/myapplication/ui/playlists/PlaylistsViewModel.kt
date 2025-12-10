package com.example.myapplication.ui.playlists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.db.DatabaseMock
import com.example.myapplication.data.repository.PlaylistsRepositoryImpl
import com.example.myapplication.data.repository.TracksRepositoryImpl
import com.example.myapplication.domain.api.PlaylistsRepository
import com.example.myapplication.domain.api.TracksRepository
import com.example.myapplication.domain.models.Playlist
import com.example.myapplication.domain.models.Track
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class PlaylistsViewModel() : ViewModel() {
    private val playlistsRepository: PlaylistsRepository = PlaylistsRepositoryImpl(scope = viewModelScope)
    private val tracksRepository: TracksRepository = TracksRepositoryImpl(scope = viewModelScope)
    // Используем мок базы вместо репозитория (как в задании) - хотя это странно, но делаем как в примере
    private val databaseRepository: DatabaseMock = DatabaseMock(scope = viewModelScope)

    val playlists: Flow<List<Playlist>> = flow {
        val collectedPlaylists = mutableListOf<Playlist>()
        playlistsRepository.getAllPlaylists().collect { playlist ->
            collectedPlaylists.addAll(playlist)
            emit(collectedPlaylists.toList())
        }
    }

    val favoriteList: Flow<List<Track>> = databaseRepository.getFavoriteTracks()

    fun createNewPlayList(namePlaylist: String, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            playlistsRepository.addNewPlaylist(namePlaylist, description)
        }
    }

    suspend fun insertTrackToPlaylist(track: Track, playlistId: Long) {
        tracksRepository.insertTrackToPlaylist(track, playlistId)
    }

    suspend fun toggleFavorite(track: Track, isFavorite: Boolean) {
        tracksRepository.updateTrackFavoriteStatus(track, isFavorite)
    }

    suspend fun deleteTrackFromPlaylist(track: Track) {
        tracksRepository.deleteTrackFromPlaylist(track)
    }

    suspend fun deletePlaylistById(id: Long) {
        tracksRepository.deleteTracksByPlaylistId(id)
        playlistsRepository.deletePlaylistById(id)
    }

    suspend fun isExist(track: Track): Track? {
        return tracksRepository.getTrackByNameAndArtist(track = track).firstOrNull()
    }
}

