package com.example.myapplication.ui.playlists.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.components.PanelHeader
import com.example.myapplication.ui.playlists.components.PlaylistListItem
import com.example.myapplication.ui.playlists.viewModel.PlaylistsViewModel
import com.example.myapplication.ui.theme.FloatingButtonColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaylistsScreen(
    modifier: Modifier = Modifier,
    playlistsViewModel: PlaylistsViewModel,
    addNewPlaylist: () -> Unit,
    navigateToPlaylist: (Long) -> Unit,
    navigateBack: () -> Unit
) {
    val playlists by playlistsViewModel.playlists.collectAsState(initial = emptyList())
    var showMergeSheet by remember { mutableStateOf(false) }
    var sourcePlaylistId by remember { mutableStateOf<Long?>(null) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            PanelHeader(
                title = stringResource(R.string.playlists),
                onClickAction = navigateBack
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 3.dp)
        ) {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp)
            ) {
                items(playlists) { playlist ->
                    PlaylistListItem(
                        playlist = playlist,
                        onClick = { navigateToPlaylist(playlist.id) },
                        onLongClick = {
                            sourcePlaylistId = playlist.id
                            showMergeSheet = true
                        }
                    )
                }
            }
            FloatingActionButton(
                modifier = Modifier
                    .padding(bottom = 30.dp, end = 17.dp)
                    .align(Alignment.BottomEnd),
                onClick = addNewPlaylist,
                containerColor = FloatingButtonColor,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.add_playlist)
                )
            }
        }

        if (showMergeSheet && sourcePlaylistId != null) {
            val sheetState = rememberModalBottomSheetState()
            val availablePlaylists = playlists.filter { it.id != sourcePlaylistId }
            ModalBottomSheet(
                onDismissRequest = { showMergeSheet = false },
                sheetState = sheetState,
                containerColor = Color.White
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                ) {
                    Text(
                        text = stringResource(R.string.merge_playlists),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1A1B22)
                    )

                    if (availablePlaylists.isEmpty()) {
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
                            items(availablePlaylists) { playlist ->
                                PlaylistListItem(
                                    playlist = playlist,
                                    onClick = {
                                        scope.launch {
                                            val message = playlistsViewModel.mergePlaylists(
                                                sourcePlaylistId!!,
                                                playlist.id
                                            )
                                            showMergeSheet = false
                                            sourcePlaylistId = null
                                            if (message != null) {
                                                Toast.makeText(
                                                    context,
                                                    message,
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }
                                    }
                                )
                                HorizontalDivider()
                            }
                        }
                    }
                }
            }
        }
    }
}
