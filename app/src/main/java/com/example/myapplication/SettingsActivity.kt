package com.example.myapplication

import android.R.attr.label
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.rememberThemeState

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val (isDark, toggleTheme) = rememberThemeState()
            val label = intent.getStringExtra("label") ?: "Настройки"
            MyApplicationTheme(
                darkTheme = isDark
            ) {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.surface
                ) { innerPadding ->
                    PanelHeader(
                        title = label,
                        modifier = Modifier
                            .padding(innerPadding),
                        isBackButton = true,
                        onBackPressed = {onBackPressedDispatcher.onBackPressed()}
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PanelHeaderPreview3() {
    PanelHeader(title = "label",
        isBackButton = true,
        onBackPressed = {}
    )
}