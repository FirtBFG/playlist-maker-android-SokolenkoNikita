package com.example.myapplication.ui.search.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myapplication.domain.models.Track

@Composable
fun TrackList(
    tracks: List<Track>,
    onTrackClick: (Track) -> Unit = {}
) {
    LazyColumn (
        modifier = Modifier.fillMaxSize()
    ) {
        items(tracks.size) { index ->
            TrackListItem(track = tracks[index], onTrackClick = onTrackClick)
        }
    }
}
