package com.example.myapplication.domain.impl

import com.example.myapplication.domain.api.TrackRepository
import com.example.myapplication.domain.api.TrackSearchInteractor
import com.example.myapplication.domain.models.Track

class TrackSearchInteractorImpl (private val repository: TrackRepository) : TrackSearchInteractor {
    override fun searchTracks(expression: String): List<Track> {
        return repository.searchTrecks(expression)
    }

    override suspend fun getAllTracks(): List<Track> {
        return repository.getALlTracks()
    }
}