package com.example.myapplication.data.network

import com.example.myapplication.creator.Storage
import com.example.myapplication.domain.NetworkClient
import com.example.myapplication.data.dto.BaseResponse
import com.example.myapplication.data.dto.TracksSearchRequest
import com.example.myapplication.data.dto.TracksSearchResponse

class RetrofitNetworkClient(private val storage: Storage) : NetworkClient {
    override fun doRequest(request: Any): TracksSearchResponse {
        val searchList = storage.search(request = (request as TracksSearchRequest).expression)
        return TracksSearchResponse(searchList).apply { resultCode = 200 }
    }
}