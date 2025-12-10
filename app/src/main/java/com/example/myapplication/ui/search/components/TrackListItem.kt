package com.example.myapplication.ui.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.domain.models.Track
import com.example.myapplication.R


@Composable
fun TrackListItem(track: Track, onTrackClick: (Track) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(61.dp)
            .padding(start = 13.dp, end = 12.dp)
            .clickable { onTrackClick(track) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AsyncImage(
            model = track.artworkUrl100,
            contentDescription = "${stringResource(id = R.string.track)} ${track.trackName}",
            placeholder = painterResource(id = R.drawable.ic_music),
            error = painterResource(id = R.drawable.ic_music),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(45.dp)
                .clip(RoundedCornerShape(2.dp))
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                track.trackName,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Row() {
                Text(
                    track.artistName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Text(
                    " • ",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Text(
                    track.trackTime, style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.tertiary,
        )

    }
}

@Preview(showBackground = true)
@Composable
fun TrackListItemPreview() {
    TrackListItem(Track(
        trackName = "Aboba",
        artistName = "DJ Donbass",
        trackTime = "4:20"
    ), {})
}
