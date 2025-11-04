package com.example.myapplication.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.myapplication.ui.navigation.PlaylistHost
import com.example.myapplication.ui.theme.PlaylistmakerandroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaylistmakerandroidTheme(
                darkTheme = false
            ) {
                PlaylistHost()
            }
        }
    }
}