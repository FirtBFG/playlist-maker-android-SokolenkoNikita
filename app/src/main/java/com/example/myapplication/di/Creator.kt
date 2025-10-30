package com.example.myapplication.di

import com.example.myapplication.data.TrackRepositoryImpl
import com.example.myapplication.data.network.RetrofitNetworkClient
import com.example.myapplication.domain.api.TrackRepository
import com.example.myapplication.domain.api.TrackSearchInteractor
import com.example.myapplication.domain.impl.TrackSearchInteractorImpl

object Creator {
    private fun getTracksRepository(): TrackRepository {
        return TrackRepositoryImpl(RetrofitNetworkClient())
    }

    fun provideTrackSearchInteractor(): TrackSearchInteractor {
        return TrackSearchInteractorImpl(getTracksRepository())
    }
}