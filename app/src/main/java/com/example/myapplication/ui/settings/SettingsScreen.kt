package com.example.myapplication.ui.settings
import PanelHeader
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
import com.example.myapplication.ui.settings.components.SettingsColumnItem
import com.example.myapplication.ui.settings.components.ToggleThemeColumnItem
import com.example.myapplication.ui.theme.PlaylistmakerandroidTheme
//import com.example.myapplication.ui.theme.rememberThemeState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.ui.settings.viewModel.SettingsViewModel

@Composable
fun SettingsScreen(onBackClickAction: () -> Unit) {
    val settingsViewModel = viewModel<SettingsViewModel>()
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            PanelHeader(
                title = stringResource(id = R.string.title_activity_settings),
                onClickAction = onBackClickAction
            )
        }
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding),
        ) {
           SettingsColumn(viewModel = settingsViewModel)
        }
    }
}


@Composable
fun SettingsColumn(viewModel: SettingsViewModel) {
    val context = LocalContext.current
    val shareAppText = stringResource(id = R.string.share_app)
    val typeSupportText = stringResource(id = R.string.type_support)
    val titleSupportText = stringResource(id = R.string.support_mail_title)
    val supportEmail = stringResource(id = R.string.support_mail_text)
    val offerRef = stringResource(id = R.string.offer_ref).toUri()

    var isDark by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        ToggleThemeColumnItem(
            text = stringResource(id = R.string.black_theme),
            isDark = isDark,
            onCheckedChange = {isDark = !isDark},
        )
        SettingsColumnItem(
            text = stringResource(id = R.string.share_app),
            icon = Icons.Default.Share,
            onClick = {
                viewModel.onShare(
                    context = context,
                    text = shareAppText,
                )
            }
        )
        SettingsColumnItem(
            text = stringResource(id = R.string.type_support),
            icon = Icons.Default.SupportAgent,
            onClick = {
                viewModel.onHelp(
                    context,
                    text = typeSupportText,
                    title = titleSupportText,
                    email = supportEmail
                )
            }
        )
        SettingsColumnItem(
            text = stringResource(id = R.string.user_offer),
            icon = Icons.AutoMirrored.Default.KeyboardArrowRight,
            onClick = {
                viewModel.onOffer(
                    context,
                    ref = offerRef
                )
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PanelHeaderPreview3() {
    PanelHeader(title = "label",
        onClickAction = {}
    )
}

@Preview(showBackground = true)
@Composable
fun SettingsColumnItemView() {
    SettingsColumnItem(icon = Icons.AutoMirrored.Default.KeyboardArrowRight, text = "Пользовательское соглашение", onClick = {})
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun ToggleThemeColumnItemView() {
    PlaylistmakerandroidTheme(darkTheme = false) {
        ToggleThemeColumnItem(text = "Тёмная тема", isDark = false, onCheckedChange = {})
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF1A1B22)
@Composable
fun ToggleThemeColumnItemViewDark() {
    PlaylistmakerandroidTheme(darkTheme = true) {
        ToggleThemeColumnItem(text = "Тёмная тема", isDark = true, onCheckedChange = {})
    }
}