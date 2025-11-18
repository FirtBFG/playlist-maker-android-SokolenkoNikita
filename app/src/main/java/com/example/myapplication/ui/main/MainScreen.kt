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
fun MainScreen(onSettingsClickAction: () -> Unit, onSearchClickAction: () -> Unit) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            MainPanelHeader(
                title = stringResource(id = R.string.app_name)
            )
            // PlaylistScreen()
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