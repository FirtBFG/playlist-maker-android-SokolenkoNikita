package com.example.myapplication.data.repository

import com.example.myapplication.data.preferences.SearchHistoryPreferences
import com.example.myapplication.domain.api.SearchHistoryRepository

class SearchHistoryRepositoryImpl(
    private val searchHistoryPreferences: SearchHistoryPreferences
) : SearchHistoryRepository {

    override fun addEntry(word: String) {
        searchHistoryPreferences.addEntry(word)
    }

    override suspend fun getEntries(): List<String> {
        return searchHistoryPreferences.getEntries()
    }
}

