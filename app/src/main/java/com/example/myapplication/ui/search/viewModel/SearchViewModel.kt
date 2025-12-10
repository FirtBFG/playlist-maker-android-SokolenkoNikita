package com.example.myapplication.ui.search.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication.creator.Creator
import com.example.myapplication.domain.api.TrackRepository
import com.example.myapplication.ui.search.state.SearchState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class SearchViewModel(
    private val trackRepository: TrackRepository,
) : ViewModel() {
    private val _searchScreenState = MutableStateFlow<SearchState>(SearchState.Initial)
    val searchScreenState = _searchScreenState.asStateFlow()
    private var lastQuery: String = ""
    private var lastFailedQuery: String = ""

    fun search(whatSearch: String) {
        if (whatSearch.isBlank()) {
            _searchScreenState.update { SearchState.Initial }
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _searchScreenState.update { SearchState.Searching }
                lastQuery = whatSearch
                val list = trackRepository.searchTracks(whatSearch.trim())
                if (list.isEmpty()) {
                    _searchScreenState.update { SearchState.EmptyList }
                } else {
                    _searchScreenState.update { SearchState.Success(list) }
                }
            } catch (e: IOException) {
                lastFailedQuery = whatSearch
                _searchScreenState.update { SearchState.Error(e.message.toString()) }
            }
        }
    }

    fun retryLastFailed() {
        if (lastFailedQuery.isNotBlank()) {
            search(lastFailedQuery)
        }
    }

    fun clearState() {
        _searchScreenState.update { SearchState.Initial }
        lastQuery = ""
        lastFailedQuery = ""
    }

    companion object {
        fun getViewModelFactory(): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return SearchViewModel(Creator.getTracksRepository()) as T
                }
            }
    }
}