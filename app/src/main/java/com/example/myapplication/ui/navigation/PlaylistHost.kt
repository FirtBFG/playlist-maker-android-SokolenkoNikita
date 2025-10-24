package com.example.myapplication.ui.navigation

import android.R.attr.name
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.screens.MainScreen
import com.example.myapplication.ui.screens.SearchScreen
import com.example.myapplication.ui.screens.SettingsScreen

@Composable
fun PlaylistHost(
    startDestination: String = ScreenEnum.MainScreen.name,
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(
            route = ScreenEnum.MainScreen.name
        ) {
            MainScreen(
                onSettingsClickAction = {navController.navigate(ScreenEnum.SettingsScreen.name)},
                onSearchClickAction = {navController.navigate(ScreenEnum.SearchScreen.name)}
            )
        }
        composable(
            route = ScreenEnum.SettingsScreen.name
        ) {
            SettingsScreen(
                onBackClickAction = {navController.navigate(ScreenEnum.MainScreen.name)}
            )
        }
        composable(
            route = ScreenEnum.SearchScreen.name
        ) {
            SearchScreen(
                onBackClickAction = {navController.navigate(ScreenEnum.MainScreen.name)}
            )
        }
    }
}