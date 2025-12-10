package com.example.myapplication.creator

import com.example.myapplication.PlaylistMakerApp
import com.example.myapplication.dataStore
import com.example.myapplication.data.preferences.SearchHistoryPreferences
import com.example.myapplication.data.repository.SearchHistoryRepositoryImpl
import com.example.myapplication.data.network.TrackRepositoryImpl
import com.example.myapplication.data.network.RetrofitNetworkClient
import com.example.myapplication.domain.api.SearchHistoryRepository
import com.example.myapplication.domain.api.TrackRepository

object Creator {
    fun getTracksRepository(): TrackRepository {
        return TrackRepositoryImpl(RetrofitNetworkClient())
    }

    fun getSearchHistoryRepository(): SearchHistoryRepository {
        val context = PlaylistMakerApp.instance
        val searchHistoryPreferences = SearchHistoryPreferences(context.dataStore)
        return SearchHistoryRepositoryImpl(searchHistoryPreferences)
    }
}