package com.example.myapplication.domain.api

import com.example.myapplication.domain.models.Track

interface TrackRepository {
    fun searchTrecks(expression: String) : List<Track>

    suspend fun getALlTracks() : List<Track>
}