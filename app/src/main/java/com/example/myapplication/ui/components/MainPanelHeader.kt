package com.example.myapplication.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.Blue40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPanelHeader(title: String) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Blue40,
            titleContentColor = MaterialTheme.colorScheme.surface
        ),
        title = { Text(
            title,
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 22.sp),
        ) },
    )
}