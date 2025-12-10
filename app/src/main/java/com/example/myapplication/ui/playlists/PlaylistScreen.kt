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
import java.util.concurrent.TimeUnit

@Composable
fun PlaylistScreen(
    playlistViewModel: PlaylistViewModel,
    onBackClick: () -> Unit,
    onTrackClick: (Track) -> Unit
) {
    val playlist by playlistViewModel.playlist.collectAsState(initial = null)

    if (playlist != null) {
        val currentPlaylist = playlist!!
        val totalMinutes = currentPlaylist.tracks.sumOf { 
             // Парсинг времени "mm:ss" в минуты
             val parts = it.trackTime.split(":")
             if (parts.size == 2) {
                 parts[0].toInt()
             } else 0
        }
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEBEBEB)) // Цвет фона как в эталоне (примерно)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(Color.Gray) // Плейсхолдер фона
            ) {
                 // Обложка плейлиста (берем первую обложку трека или заглушку)
                 val coverUrl = if (currentPlaylist.tracks.isNotEmpty()) currentPlaylist.tracks.first().artworkUrl100 else null
                 
                 AsyncImage(
                    model = coverUrl,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.ic_music),
                    error = painterResource(R.drawable.ic_music)
                )

                // Кнопка назад
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.TopStart)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = currentPlaylist.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                if (currentPlaylist.description.isNotEmpty()) {
                    Text(
                        text = currentPlaylist.description,
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                
                Row(
                    modifier = Modifier.padding(top = 8.dp),
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
                        text = "${currentPlaylist.tracks.size} треков",
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                }
                
                Row(
                    modifier = Modifier.padding(top = 16.dp),
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
            
            // Список треков
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color.White)
            ) {
                 items(currentPlaylist.tracks) { track ->
                     TrackListItem(track = track, onTrackClick = onTrackClick)
                 }
            }
        }
    } else {
        // Loading or Empty state
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

