package com.example.myapplication.ui.playlists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.PlaylistsRepositoryImpl
import com.example.myapplication.domain.api.PlaylistsRepository
import com.example.myapplication.domain.models.Playlist
import kotlinx.coroutines.flow.Flow

class PlaylistViewModel(
    private val playlistId: Long
) : ViewModel() {
    
    private val playlistsRepository: PlaylistsRepository = PlaylistsRepositoryImpl(scope = viewModelScope)
    
    val playlist: Flow<Playlist?> = playlistsRepository.getPlaylist(playlistId)

    companion object {
        fun getViewModelFactory(playlistId: Long): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return PlaylistViewModel(playlistId) as T
                }
            }
    }
}

