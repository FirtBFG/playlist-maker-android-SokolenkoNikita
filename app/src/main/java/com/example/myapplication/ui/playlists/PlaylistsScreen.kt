package com.example.myapplication.ui.playlists

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.outlined.ChevronRight
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.domain.models.Playlist

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
fun PlaylistListItem(playlist: Playlist, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(61.dp)
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFFEAEAEA))
            ) {
                if (playlist.coverImageUri != null) {
                    AsyncImage(
                        model = Uri.parse(playlist.coverImageUri),
                        contentDescription = playlist.name,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    val coverUrl = if (playlist.tracks.isNotEmpty()) playlist.tracks.first().artworkUrl100 else null
                    if (!coverUrl.isNullOrBlank()) {
                        AsyncImage(
                            model = coverUrl,
                            contentDescription = playlist.name,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop,
                            placeholder = painterResource(R.drawable.ic_music),
                            error = painterResource(R.drawable.ic_music)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Filled.MusicNote,
                            contentDescription = playlist.name,
                            tint = Color(0xFFB5B5B6),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
            Spacer(Modifier.width(10.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = playlist.name,
                    fontSize = 16.sp,
                    color = Color(0xFF111827),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = getTracksDeclension(playlist.tracks.size),
                    fontSize = 11.sp,
                    color = Color(0xFFB0B1B5),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Icon(
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = Color(0xFFB5B5B6),
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun PlaylistsScreen(
    modifier: Modifier = Modifier,
    playlistsViewModel: PlaylistsViewModel,
    addNewPlaylist: () -> Unit,
    navigateToPlaylist: (Long) -> Unit,
    navigateBack: () -> Unit
) {
    val headerHeight = 80.dp
    val cornerRadius = 16.dp
    val playlists by playlistsViewModel.playlists.collectAsState(initial = emptyList())

    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(headerHeight + cornerRadius)
                .background(color = Color.White)
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
                        .clickable(onClick = navigateBack)
                )
                Spacer(modifier = Modifier.width(28.dp))
                Text(
                    text = "Плейлисты",
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.W500
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = headerHeight + 3.dp)
                .background(Color.White)
        ) {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp)
            ) {
                items(playlists) { playlist ->
                    PlaylistListItem(playlist = playlist) {
                        navigateToPlaylist(playlist.id)
                    }
                }
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .padding(32.dp)
                .align(Alignment.BottomEnd),
            onClick = addNewPlaylist,
            containerColor = Color.Gray,
            contentColor = Color.White,
            shape = CircleShape
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(R.string.add_playlist)
            )
        }
    }
}
