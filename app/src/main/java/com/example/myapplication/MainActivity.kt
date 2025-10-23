package com.example.myapplication
import PanelHeader
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import com.example.myapplication.ui.components.MenuColumnItem
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
                Scaffold(
                    modifier = Modifier
                    .fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background
                ) { innerPadding ->
                    Column (
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        PanelHeader(
                            title = stringResource(R.string.app_name),
                        )
                        MainMenu()
                    }
                }
            }
        }
    }
}

@Composable
fun MainMenu() {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 16.dp)
            .padding(top = 8.dp),
    ) {
        Column {
            MenuColumnItem(
                icon = Icons.Default.Search,
                text = "Поиск",
                onClick = {
                    val searchIntent = Intent(context, SearchActivity::class.java)
                    searchIntent.putExtra("label", "Поиск")
                    context.startActivity(searchIntent)
                }
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
                text = "Настройки",
                onClick = {
                    val settingsIntent = Intent(context, SettingsActivity::class.java)
                    settingsIntent.putExtra("lable", "Настройки")
                    context.startActivity(settingsIntent)
                }
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF3771E5)
@Composable
fun PanelHeaderPreview() {
    MyApplicationTheme(darkTheme = false) {
        PanelHeader(stringResource(R.string.app_name))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PanelHeaderPreview2() {
    MyApplicationTheme(darkTheme = false) {
        PanelHeader(stringResource(R.string.app_name), isBackButton = true)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF3771E5)
@Composable
fun MainMenuPreview() {
    MyApplicationTheme(darkTheme = false) {
        MainMenu()
    }
}