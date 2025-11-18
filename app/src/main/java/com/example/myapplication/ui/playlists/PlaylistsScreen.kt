package com.example.myapplication.ui.playlists

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PlaylistScreen(modifier: Modifier = Modifier) {
    var showBottomSheet by remember { mutableStateOf(false) }
    Scaffold(modifier = modifier.fillMaxSize(), containerColor = Color.White) { innerPadding ->
        MyFloatingActionButton(
            modifier =modifier.padding(innerPadding)
        ) {
            showBottomSheet = true
        }
        ModalSheet(
            modifier = modifier.padding(innerPadding),
            isShowPanel = showBottomSheet,
            content = "bottom sheet",
            onDismissRequest = {showBottomSheet = false}
        )
    }
}

@Composable
fun MyFloatingActionButton(
    modifier: Modifier,
    callback: () -> Unit
) {

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        FloatingActionButton(
            modifier = Modifier
                .padding(32.dp)
                .align(Alignment.BottomEnd),
            onClick = callback,
            containerColor = Color.LightGray,
            contentColor = Color.White,
            shape = CircleShape
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Floating Action Button",
                modifier = Modifier.size(51.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalSheet(
    modifier: Modifier,
    isShowPanel: Boolean,
    onDismissRequest: () -> Unit,
    content: String
) {
    val sheetState = rememberModalBottomSheetState()

    if(isShowPanel) {
        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            sheetState = sheetState
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = content,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Icon(
                    modifier = Modifier
                        .size(32.dp)
                        .padding(top = 16.dp),
                    imageVector = Icons.Default.Home,
                    contentDescription = "Icon"
                )
            }
        }
    }

}

@Preview
@Composable
fun ScreenPreview() {
    PlaylistScreen()
}