package com.example.myapplication.domain.models

data class Playlist(
    val id: Long = 0,
    val name: String,
    val description: String,
    val creationYear: Int? = null,
    var tracks: List<Track>
)

