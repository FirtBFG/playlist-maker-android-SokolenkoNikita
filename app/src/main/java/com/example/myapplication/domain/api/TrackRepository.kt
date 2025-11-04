package com.example.myapplication.domain.api

import com.example.myapplication.domain.models.Track

interface TrackRepository {
    suspend fun searchTracks(expression: String) : List<Track>
}