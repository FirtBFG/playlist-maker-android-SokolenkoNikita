package com.example.myapplication.ui.details.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.PlaylistAdd
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.PlaylistAdd
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.domain.models.Track
import com.example.myapplication.ui.components.PanelHeader
import com.example.myapplication.ui.main.components.MainPanelHeader
import com.example.myapplication.ui.playlists.viewModel.PlaylistsViewModel
import kotlinx.coroutines.launch
import com.example.myapplication.ui.details.components.TrackInfoRow
import com.example.myapplication.ui.theme.YPLightGray
import com.example.myapplication.ui.theme.YPTextGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackDetailsScreen(
    track: Track,
    viewModel: PlaylistsViewModel,
    onBackClick: () -> Unit
) {
    var showSheet by remember { mutableStateOf(false) }
    val playlists by viewModel.playlists.collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()

    val trackFlow = remember(track.trackName, track.artistName) {
        viewModel.getTrackFromDb(track)
    }
    val trackFromDb by trackFlow.collectAsState(initial = null)
    val currentTrack = trackFromDb ?: track
    var isFavorite by remember(currentTrack) { mutableStateOf(currentTrack.favorite) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            PanelHeader(
                title = "",
                onClickAction = onBackClick
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFE8E8E8))
                ) {
                    if (track.artworkUrl100.isNotBlank()) {
                        val artworkUrl = track.artworkUrl100.replace("100x100", "512x512")
                        AsyncImage(
                            model = artworkUrl,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop,
                            placeholder = painterResource(R.drawable.ic_music),
                            error = painterResource(R.drawable.ic_music)
                        )
                    } else {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.MusicNote,
                                contentDescription = null,
                                tint = Color(0xFFB5B5B6),
                                modifier = Modifier.size(80.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = currentTrack.trackName,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF1A1B22),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = currentTrack.artistName,
                    fontSize = 14.sp,
                    color = Color(0xFF6B7280),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(YPLightGray)
                            .clickable { showSheet = true },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.PlaylistAdd,
                            contentDescription = null,
                            tint = Color(0xFF9CA3AF),
                            modifier = Modifier.size(28.dp)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(
                                if (isFavorite) YPLightGray else Color(0xFFF3F4F6)
                            )
                            .clickable {
                                isFavorite = !isFavorite
                                scope.launch {
                                    viewModel.toggleFavorite(currentTrack, isFavorite)
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = null,
                            tint = if (isFavorite) Color(0xFFE11D48) else Color(0xFF9CA3AF),
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        TrackInfoRow(stringResource(R.string.duration), currentTrack.trackTime)
                        if (currentTrack.collectionName.isNotBlank()) {
                            Spacer(modifier = Modifier.height(12.dp))
                            TrackInfoRow(stringResource(R.string.album), currentTrack.collectionName)
                        }
                        if (currentTrack.primaryGenreName.isNotBlank()) {
                            Spacer(modifier = Modifier.height(12.dp))
                            TrackInfoRow(stringResource(R.string.genre), currentTrack.primaryGenreName)
                        }
                        if (currentTrack.country.isNotBlank()) {
                            Spacer(modifier = Modifier.height(12.dp))
                            TrackInfoRow(stringResource(R.string.country), currentTrack.country)
                        }
                    }
                }
            }

            if (showSheet) {
                val sheetState = rememberModalBottomSheetState()
                ModalBottomSheet(
                    onDismissRequest = { showSheet = false },
                    sheetState = sheetState,
                    containerColor = Color.White
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.add_to_playlist),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF1A1B22)
                        )

                        if (playlists.isEmpty()) {
                            Text(
                                text = stringResource(R.string.no_playlists),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(32.dp),
                                textAlign = TextAlign.Center,
                                fontSize = 14.sp,
                                color = Color(0xFF9CA3AF)
                            )
                        } else {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            ) {
                                items(playlists) { playlist ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable {
                                                scope.launch {
                                                    viewModel.insertTrackToPlaylist(currentTrack, playlist.id)
                                                    showSheet = false
                                                }
                                            }
                                            .padding(vertical = 12.dp, horizontal = 8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(40.dp)
                                                .clip(RoundedCornerShape(8.dp))
                                                .background(Color(0xFFE8E8E8)),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = Icons.Filled.MusicNote,
                                                contentDescription = null,
                                                tint = Color(0xFFB5B5B6),
                                                modifier = Modifier.size(20.dp)
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(12.dp))
                                        Column {
                                            Text(
                                                text = playlist.name,
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.Medium,
                                                color = Color(0xFF1A1B22)
                                            )
                                            Text(
                                                text = "${playlist.tracks.size} треков",
                                                fontSize = 12.sp,
                                                color = Color(0xFF9CA3AF)
                                            )
                                        }
                                    }
                                    HorizontalDivider(color = Color(0xFFF3F4F6))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
