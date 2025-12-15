package com.example.myapplication.ui.playlists.helpers

import com.example.myapplication.domain.models.Track

fun getTracksDeclension(count: Int): String {
    val lastDigit = count % 10
    val lastTwoDigits = count % 100

    return when {
        lastTwoDigits in 11..14 -> "$count треков"
        lastDigit == 1 -> "$count трек"
        lastDigit in 2..4 -> "$count трека"
        else -> "$count треков"
    }
}

fun calculateTotalMinutes(tracks: List<Track>): Int {
    return tracks.sumOf {
        val parts = it.trackTime.split(":")
        if (parts.size == 2) {
            parts[0].toInt()
        } else 0
    }
}