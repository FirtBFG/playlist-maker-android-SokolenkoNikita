package com.example.myapplication.data.network

import com.example.myapplication.data.NetworkClient
import com.example.myapplication.data.dto.BaseResponse
import com.example.myapplication.data.dto.TracksSearchResponse

class RetrofitNetworkClient : NetworkClient {
    override fun doRequest(dto: Any): BaseResponse {
        return TracksSearchResponse(listOf())
    }
}