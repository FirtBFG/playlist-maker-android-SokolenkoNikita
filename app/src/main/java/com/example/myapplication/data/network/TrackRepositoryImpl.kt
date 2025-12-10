package com.example.myapplication.data.network

import com.example.myapplication.domain.NetworkClient
import com.example.myapplication.data.dto.TracksSearchRequest
import com.example.myapplication.data.dto.TracksSearchResponse
import com.example.myapplication.domain.api.TrackRepository
import com.example.myapplication.domain.models.Track
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class TrackRepositoryImpl (private val networkClient: NetworkClient) : TrackRepository {
    override suspend fun searchTracks(expression: String): List<Track> {
        val response = networkClient.doRequest(TracksSearchRequest(expression))
        // delay(1000) // Задержка не нужна для реального запроса
        return if(response.resultCode == 200) {
            (response as TracksSearchResponse).results.map {
                Track(
                    id = it.trackId ?: System.currentTimeMillis(),
                    artistName = it.artistName,
                    trackName = it.trackName,
                    trackTime = SimpleDateFormat("mm:ss", Locale.getDefault()).apply {
                        timeZone = TimeZone.getTimeZone("GMT")
                    }.format(it.trackTimeMillis),
                    artworkUrl100 = it.artworkUrl100 ?: "",
                    collectionName = it.collectionName ?: "",
                    releaseDate = it.releaseDate ?: "",
                    primaryGenreName = it.primaryGenreName ?: "",
                    country = it.country ?: "",
                    previewUrl = it.previewUrl ?: ""
                )
            }
        } else {
            emptyList()
        }
    }
}
