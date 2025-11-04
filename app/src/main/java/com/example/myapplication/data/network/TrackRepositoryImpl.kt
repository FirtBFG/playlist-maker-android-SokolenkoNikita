package com.example.myapplication.data.network

import com.example.myapplication.domain.NetworkClient
import com.example.myapplication.data.dto.TracksSearchRequest
import com.example.myapplication.data.dto.TracksSearchResponse
import com.example.myapplication.domain.api.TrackRepository
import com.example.myapplication.domain.models.Track
import kotlinx.coroutines.delay


class TrackRepositoryImpl (private val networkClient: NetworkClient) : TrackRepository {
    override suspend fun searchTrecks(expression: String): List<Track> {
        val response = networkClient.doRequest(TracksSearchRequest(expression))
        delay(1000)
        return if(response.resultCode == 200) {
            (response as TracksSearchResponse).results.map {
                val seconds = it.trackTimeMillis / 1000
                val minutes = seconds / 60
                Track(
                    artistName = it.artistName,
                    trackName = it.trackName,
                    trackTime = "%02d".format(minutes) + "%02d".format(seconds - minutes*60)
                )
            }
        } else {
            emptyList()
        }
    }
}