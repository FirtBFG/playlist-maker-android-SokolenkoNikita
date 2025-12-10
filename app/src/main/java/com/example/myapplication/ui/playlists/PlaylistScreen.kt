package com.example.myapplication.ui.playlists

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.domain.models.Track
import com.example.myapplication.ui.search.components.TrackListItem
import java.util.Calendar

private fun calculateTotalMinutes(tracks: List<Track>): Int {
    return tracks.sumOf {
        val parts = it.trackTime.split(":")
        if (parts.size == 2) {
            parts[0].toInt()
        } else 0
    }
}

private fun getTracksDeclension(count: Int): String {
    val lastDigit = count % 10
    val lastTwoDigits = count % 100
    return when {
        lastTwoDigits in 11..14 -> "$count треков"
        lastDigit == 1 -> "$count трек"
        lastDigit in 2..4 -> "$count трека"
        else -> "$count треков"
    }
}

@Composable
fun PlaylistScreen(
    playlistViewModel: PlaylistViewModel,
    onBackClick: () -> Unit,
    onTrackClick: (Track) -> Unit
) {
    val playlist by playlistViewModel.playlist.collectAsState(initial = null)
    val headerHeight = 80.dp
    val cornerRadius = 16.dp

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFFEBEBEB))) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(headerHeight + cornerRadius)
                .background(color = Color(0xFFEBEBEB))
                .padding(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Назад",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(onClick = onBackClick)
                )
                Spacer(modifier = Modifier.width(28.dp))
                // В эталоне заголовок "Плейлист" или имя плейлиста в шапке?
                // В эталоне: text = playlist?.name ?: "Плейлист"
                // Но ниже еще раз имя.
                // Судя по коду эталона, в шапке показывается имя.
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = headerHeight) // Отступ под шапку
        ) {
            if (playlist != null) {
                val currentPlaylist = playlist!!
                val totalMinutes = calculateTotalMinutes(currentPlaylist.tracks)
                val tracksCount = currentPlaylist.tracks.size

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    item {
                        Column(
                            modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp)
                        ) {
                            // Обложка
                            val coverUrl = if (currentPlaylist.tracks.isNotEmpty()) currentPlaylist.tracks.first().artworkUrl100 else null

                            AsyncImage(
                                model = coverUrl,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color.Gray),
                                contentScale = ContentScale.Crop,
                                placeholder = painterResource(R.drawable.ic_music),
                                error = painterResource(R.drawable.ic_music)
                            )

                            Spacer(modifier = Modifier.height(24.dp))

                            Text(
                                text = currentPlaylist.name,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            
                            if (currentPlaylist.description.isNotEmpty()) {
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = currentPlaylist.description,
                                    fontSize = 18.sp,
                                    color = Color.Black
                                )
                            }
                            
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            val creationYear = Calendar.getInstance().get(Calendar.YEAR) // В модели нет года, берем текущий или заглушку
                            // В эталоне: val creationYear = currentPlaylist.creationYear ?: Calendar.getInstance().get(Calendar.YEAR)
                            // У нас нет поля creationYear в Playlist (пока).

                            // Статистика
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "$totalMinutes минут",
                                    fontSize = 18.sp,
                                    color = Color.Black
                                )
                                Text(
                                    text = " • ",
                                    fontSize = 18.sp,
                                    color = Color.Black
                                )
                                Text(
                                    text = getTracksDeclension(tracksCount),
                                    fontSize = 18.sp,
                                    color = Color.Black
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Share,
                                    contentDescription = "Share",
                                    tint = Color.Black,
                                    modifier = Modifier.size(24.dp)
                                )
                                Icon(
                                    imageVector = Icons.Default.MoreVert,
                                    contentDescription = "Menu",
                                    tint = Color.Black,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    }

                    // Список треков
                    items(currentPlaylist.tracks) { track ->
                        TrackListItem(track = track, onTrackClick = onTrackClick)
                    }
                }
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

