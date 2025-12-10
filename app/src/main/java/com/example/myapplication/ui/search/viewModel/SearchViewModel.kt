package com.example.myapplication.ui.search.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication.creator.Creator
import com.example.myapplication.domain.api.SearchHistoryRepository
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
    private val searchHistoryRepository: SearchHistoryRepository
) : ViewModel() {
    private val _searchScreenState = MutableStateFlow<SearchState>(SearchState.Initial)
    val searchScreenState = _searchScreenState.asStateFlow()
    private val _searchHistory = MutableStateFlow<List<String>>(emptyList())
    val searchHistory = _searchHistory.asStateFlow()
    private var lastQuery: String = ""
    private var lastFailedQuery: String = ""

    init {
        loadSearchHistory()
    }

    private fun loadSearchHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            val history = searchHistoryRepository.getEntries()
            _searchHistory.update { history }
        }
    }

    fun search(whatSearch: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val query = whatSearch.trim()
                if (query.isEmpty()) {
                    _searchScreenState.update { SearchState.Initial }
                    loadSearchHistory()
                    return@launch
                }
                searchHistoryRepository.addEntry(query)
                loadSearchHistory()
                _searchScreenState.update { SearchState.Searching }
                lastQuery = query
                val list = trackRepository.searchTracks(query)
                _searchScreenState.update { SearchState.Success(list) }
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
        loadSearchHistory()
    }

    companion object {
        fun getViewModelFactory(): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return SearchViewModel(
                        Creator.getTracksRepository(),
                        Creator.getSearchHistoryRepository()
                    ) as T
                }
            }
    }
}