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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.components.MainPanelHeader
import com.example.myapplication.ui.components.MenuColumnItem
import com.example.myapplication.ui.theme.Blue40
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
        MainPanelHeader(stringResource(R.string.app_name))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PanelHeaderPreview2() {
    MyApplicationTheme(darkTheme = false) {
        PanelHeader(stringResource(R.string.app_name), onClickAction = {})
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF3771E5)
@Composable
fun MainMenuPreview() {
    MyApplicationTheme(darkTheme = false) {
        MainMenu()
    }
}