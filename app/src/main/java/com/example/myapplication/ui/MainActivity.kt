package com.example.myapplication.ui
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.myapplication.ui.navigation.PlaylistHost
import com.example.myapplication.ui.theme.MyApplicationTheme

// import com.example.myapplication.ui.theme.rememberThemeState


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //val (isDark, toggleTheme) = rememberThemeState()
            MyApplicationTheme(
                darkTheme = false
            ) {
                PlaylistHost()
            }
        }
    }
}

