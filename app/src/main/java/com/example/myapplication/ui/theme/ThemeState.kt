package com.example.myapplication.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@Composable
fun rememberThemeState(
    initialIsDark: Boolean = isSystemInDarkTheme()
): Pair<Boolean, () -> Unit> {
    var isDark by rememberSaveable { mutableStateOf(initialIsDark) }

    return isDark to { isDark = !isDark }
}