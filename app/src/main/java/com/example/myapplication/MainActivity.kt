package com.example.myapplication
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.rememberThemeState
import java.nio.file.WatchEvent


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val (isDark, toggleTheme) = rememberThemeState()
            MyApplicationTheme(
                darkTheme = isDark
            ) {
                Scaffold(
                    modifier = Modifier
                    .fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background
                ) { innerPadding ->
                    Column {
                        PanelHeader(
                            title = stringResource(R.string.app_name),
                            modifier = Modifier.padding(innerPadding)
                        )
                        MainMenu()
                    }
                }
            }
        }
    }
}

@Composable
fun PanelHeader(title: String, modifier: Modifier = Modifier, isBackButton: Boolean = false, onBackPressed: () -> Unit = {}) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if(isBackButton)
                IconButton(onClick = {onBackPressed()}) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                }
            Text(
                title,
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 22.sp),
                color = if(isBackButton) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.surface
            )
        }
    }
}

@Composable
fun MainMenu() {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
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
                    Toast.makeText(
                    context,
                    "Нажата кнопка \"Поиск\"",
                    Toast.LENGTH_SHORT
                    ).show()
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

@Composable
fun MenuColumnItem(icon: ImageVector, text: String, onClick: () -> Unit) {
    Box(
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .height(66.dp).clickable{onClick()},
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.weight(0.1f),
            )
            Text(
                text,
                modifier = Modifier.weight(0.8f),
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 22.sp),
                color = MaterialTheme.colorScheme.onSurface,
                )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.weight(0.1f),
                tint = Color.LightGray
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

@Preview(showBackground = true, backgroundColor = 0xFF3771E5)
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

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun ColumnItemPreview() {
    MyApplicationTheme(darkTheme = false) {
        MenuColumnItem(icon = Icons.Default.Search, text = "Поиск", onClick = {})
    }
}