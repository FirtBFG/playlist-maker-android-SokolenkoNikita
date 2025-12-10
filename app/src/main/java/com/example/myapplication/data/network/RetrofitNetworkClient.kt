package com.example.myapplication.data.network

import com.example.myapplication.data.dto.TracksSearchRequest
import com.example.myapplication.data.dto.TracksSearchResponse
import com.example.myapplication.domain.NetworkClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitNetworkClient : NetworkClient {

    private val iTunesBaseUrl = "https://itunes.apple.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(iTunesBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val iTunesService = retrofit.create(ITunesApi::class.java)

    override fun doRequest(request: Any): TracksSearchResponse {
        if (request is TracksSearchRequest) {
            val response = iTunesService.search(request.expression).execute()
            val body = response.body() ?: TracksSearchResponse(emptyList())
            return body.apply { resultCode = response.code() }
        } else {
            return TracksSearchResponse(emptyList()).apply { resultCode = 400 }
        }
    }
}