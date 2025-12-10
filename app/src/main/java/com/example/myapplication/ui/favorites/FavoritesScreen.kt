package com.example.myapplication.ui.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.myapplication.R
import com.example.myapplication.ui.components.PanelHeader
import com.example.myapplication.ui.playlists.PlaylistsViewModel
import com.example.myapplication.ui.search.components.TrackList

@Composable
fun FavoritesScreen(
    viewModel: PlaylistsViewModel,
    onBackClick: () -> Unit
) {
    val favorites by viewModel.favoriteList.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            PanelHeader(
                title = stringResource(id = R.string.saved),
                onClickAction = onBackClick
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            TrackList(tracks = favorites)
        }
    }
}

