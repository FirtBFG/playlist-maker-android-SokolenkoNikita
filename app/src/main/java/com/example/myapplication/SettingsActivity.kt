package com.example.myapplication
import PanelHeader
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.components.SettingsColumnItem
import com.example.myapplication.ui.components.ToggleThemeColumnItem
import com.example.myapplication.ui.theme.MyApplicationTheme
//import com.example.myapplication.ui.theme.rememberThemeState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //val (isDark, toggleTheme) = rememberThemeState()
            val label = intent.getStringExtra("label") ?: "Настройки"
            MyApplicationTheme(
                darkTheme = false
            ) {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.surface
                ) { innerPadding ->
                    Column (
                        modifier = Modifier
                            .padding(innerPadding),
                    ) {
                        PanelHeader(
                            title = label,
                            isBackButton = true,
                            onBackPressed = { onBackPressedDispatcher.onBackPressed() }
                        )
                        SettingsColumn()
                    }
                }
            }
        }
    }
}

@Composable
fun SettingsColumn() {
    //val (isDark, toggleTheme) = rememberThemeState()
    var isDark by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        ToggleThemeColumnItem(
            text = "Тёмная тема",
            isDark = isDark,
            onCheckedChange = {isDark = !isDark},
        )
        SettingsColumnItem(
            text = "Поделиться приложением",
            icon = Icons.Default.Share,
            onClick = {}
        )
        SettingsColumnItem(
            text = "Пользовательское соглашение",
            icon = Icons.Default.SupportAgent,
            onClick = {}
        )
        SettingsColumnItem(
            text = "Написать в поддержку",
            icon = Icons.AutoMirrored.Default.KeyboardArrowRight,
            onClick = {}
        )
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

@Preview(showBackground = true)
@Composable
fun SettingsColumnView() {
    MyApplicationTheme(darkTheme = false) {
        SettingsColumn()
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsColumnItemView() {
    SettingsColumnItem(icon = Icons.AutoMirrored.Default.KeyboardArrowRight, text = "Пользовательское соглашение", onClick = {})
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun ToggleThemeColumnItemView() {
    MyApplicationTheme(darkTheme = false) {
        ToggleThemeColumnItem(text = "Тёмная тема", isDark = false, onCheckedChange = {})
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF1A1B22)
@Composable
fun ToggleThemeColumnItemViewDark() {
    MyApplicationTheme(darkTheme = true) {
        ToggleThemeColumnItem(text = "Тёмная тема", isDark = true, onCheckedChange = {})
    }
}