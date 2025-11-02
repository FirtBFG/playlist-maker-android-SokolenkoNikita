package com.example.myapplication.domain

import com.example.myapplication.data.dto.BaseResponse

interface NetworkClient {
    fun doRequest(request: Any): BaseResponse
}