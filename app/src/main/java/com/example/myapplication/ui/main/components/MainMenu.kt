package com.example.myapplication.ui.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.PlaylistmakerandroidTheme

@Composable
fun MainMenu(
    onSettingsClickAction: () -> Unit,
    onSearchClickAction: () -> Unit,
    onPlaylistsClickAction: () -> Unit,
    onFavoritesClickAction: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(top = 14.dp)
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 16.dp)
            .padding(top = 8.dp),
    ) {
        Column {
            MenuColumnItem(
                icon = Icons.Default.Search,
                text = stringResource(id = R.string.search),
                onClick = onSearchClickAction
            )
            MenuColumnItem(
                icon = Icons.Filled.LibraryMusic,
                text = stringResource(id = R.string.playlists),
                onClick = onPlaylistsClickAction
            )
            MenuColumnItem(
                icon = Icons.Default.FavoriteBorder,
                text = stringResource(id = R.string.saved),
                onClick = onFavoritesClickAction
            )
            MenuColumnItem(
                icon = Icons.Default.Settings,
                text = stringResource(id = R.string.title_activity_settings),
                onClick = onSettingsClickAction
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF3771E5)
@Composable
fun MainMenuPreview() {
    PlaylistmakerandroidTheme(darkTheme = false) {
        MainMenu({},{},{},{})
    }
}
