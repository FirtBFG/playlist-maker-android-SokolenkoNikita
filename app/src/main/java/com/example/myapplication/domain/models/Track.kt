package com.example.myapplication.domain.models

data class Track(
    val id: Long = 0,
    val trackName: String,
    val artistName: String,
    val trackTime: String,
    val artworkUrl100: String = "",
    val collectionName: String = "",
    val releaseDate: String = "",
    val primaryGenreName: String = "",
    val country: String = "",
    val previewUrl: String = "",
    val favorite: Boolean = false,
    val playlistId: Long = 0
)
