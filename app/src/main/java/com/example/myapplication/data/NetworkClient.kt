package com.example.myapplication.data

import com.example.myapplication.data.dto.BaseResponse

interface NetworkClient {
    fun doRequest(dto: Any): BaseResponse
} 