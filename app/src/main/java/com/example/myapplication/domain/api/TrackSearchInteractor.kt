package com.example.myapplication.domain.api

import com.example.myapplication.domain.models.Track

interface TrackSearchInteractor {
    fun searchTracks(expression: String) : List<Track>

    suspend fun getAllTracks(): List<Track>
}