package com.example.myapplication.creator

import com.example.myapplication.data.network.TrackRepositoryImpl
import com.example.myapplication.data.network.RetrofitNetworkClient
import com.example.myapplication.domain.api.TrackRepository

object Creator {
    fun getTracksRepository(): TrackRepository {
        return TrackRepositoryImpl(RetrofitNetworkClient())
    }
}