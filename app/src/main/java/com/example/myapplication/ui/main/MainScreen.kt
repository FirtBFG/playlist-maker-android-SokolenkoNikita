package com.example.myapplication.ui.main

import com.example.myapplication.ui.components.PanelHeader
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.R
import com.example.myapplication.ui.main.components.MainMenu
import com.example.myapplication.ui.main.components.MainPanelHeader
import com.example.myapplication.ui.theme.PlaylistmakerandroidTheme

@Composable
fun MainScreen(
    onSettingsClickAction: () -> Unit,
    onSearchClickAction: () -> Unit,
    onPlaylistsClickAction: () -> Unit,
    onFavoritesClickAction: () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            MainPanelHeader(
                title = stringResource(id = R.string.app_name)
            )
        },
    ) { innerPadding ->
        Column (
            modifier = Modifier.padding(innerPadding)
        ) {
            MainMenu(
                onSearchClickAction = onSearchClickAction,
                onSettingsClickAction = onSettingsClickAction,
                onPlaylistsClickAction = onPlaylistsClickAction,
                onFavoritesClickAction = onFavoritesClickAction
            )
        }
    }
}
