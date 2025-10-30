package com.example.myapplication.ui.main

import PanelHeader
import android.widget.Toast
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
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.components.MainPanelHeader
import com.example.myapplication.ui.components.MenuColumnItem
import com.example.myapplication.ui.theme.PlaylistmakerandroidTheme

@Composable
fun MainScreen(onSettingsClickAction: () -> Unit, onSearchClickAction: () -> Unit) {
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
                onSettingsClickAction = onSettingsClickAction
            )
        }
    }
}

@Composable
fun MainMenu(onSettingsClickAction: () -> Unit, onSearchClickAction: () -> Unit) {
    val context = LocalContext.current
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
                text = stringResource(id = R.string.title_activity_search),
                onClick = onSearchClickAction
            )
            MenuColumnItem(
                icon = Icons.Filled.LibraryMusic,
                text = "Плейлисты",
                onClick = {
                    Toast.makeText(
                        context,
                        "Нажата кнопка \"Плейлисты\"",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
            MenuColumnItem(
                icon = Icons.Default.FavoriteBorder,
                text = "Избранное",
                onClick = {
                    Toast.makeText(
                        context,
                        "Нажата кнопка \"Избранное\"",
                        Toast.LENGTH_SHORT
                    ).show()
                }
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
fun PanelHeaderPreview() {
    PlaylistmakerandroidTheme(darkTheme = false) {
        MainPanelHeader(stringResource(R.string.app_name))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PanelHeaderPreview2() {
    PlaylistmakerandroidTheme(darkTheme = false) {
        PanelHeader(stringResource(R.string.app_name), onClickAction = {})
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF3771E5)
@Composable
fun MainMenuPreview() {
    PlaylistmakerandroidTheme(darkTheme = false) {
        MainMenu({},{})
    }
}