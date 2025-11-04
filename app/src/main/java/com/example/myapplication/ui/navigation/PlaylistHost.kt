package com.example.myapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.main.MainScreen
import com.example.myapplication.ui.search.screen.SearchScreen
import com.example.myapplication.ui.search.viewModel.SearchViewModel
import com.example.myapplication.ui.settings.SettingsScreen

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
            val viewModel = viewModel<SearchViewModel> (
                factory = SearchViewModel.getViewModelFactory()
            )
            SearchScreen(
                onBackClickAction = {navController.navigate(ScreenEnum.MainScreen.name)},
                viewModel = viewModel,
            )
        }
    }
}