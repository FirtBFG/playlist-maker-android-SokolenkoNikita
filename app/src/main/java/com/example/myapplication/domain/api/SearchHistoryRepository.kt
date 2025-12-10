package com.example.myapplication.domain.api

interface SearchHistoryRepository {
    fun addEntry(word: String)
    suspend fun getEntries(): List<String>
}

