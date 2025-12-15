package com.example.myapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.domain.models.Track
import com.example.myapplication.ui.details.screen.TrackDetailsScreen
import com.example.myapplication.ui.favorites.FavoritesScreen
import com.example.myapplication.ui.main.MainScreen
import com.example.myapplication.ui.playlists.screen.CreatePlaylistScreen
import com.example.myapplication.ui.playlists.screen.PlaylistsScreen
import com.example.myapplication.ui.playlists.viewModel.PlaylistsViewModel
import com.example.myapplication.ui.search.screen.SearchScreen
import com.example.myapplication.ui.search.viewModel.SearchViewModel
import com.example.myapplication.ui.settings.SettingsScreen
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import com.google.gson.Gson
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.myapplication.ui.playlists.screen.PlaylistScreen
import com.example.myapplication.ui.playlists.viewModel.PlaylistViewModel

@Composable
fun PlaylistHost(
    startDestination: String = ScreenEnum.MainScreen.name,
) {
    val navController = rememberNavController()
    val playlistsViewModel: PlaylistsViewModel = viewModel()
    val gson = Gson()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(
            route = ScreenEnum.MainScreen.name
        ) {
            MainScreen(
                onSettingsClickAction = {navController.navigate(ScreenEnum.SettingsScreen.name)},
                onSearchClickAction = {navController.navigate(ScreenEnum.SearchScreen.name)},
                onPlaylistsClickAction = {navController.navigate(ScreenEnum.PlaylistsScreen.name)},
                onFavoritesClickAction = {navController.navigate(ScreenEnum.FavoritesScreen.name)}
            )
        }
        composable(
            route = ScreenEnum.SettingsScreen.name
        ) {
            SettingsScreen(
                onBackClickAction = {navController.popBackStack()}
            )
        }
        composable(
            route = ScreenEnum.SearchScreen.name
        ) {
            val viewModel = viewModel<SearchViewModel> (
                factory = SearchViewModel.getViewModelFactory()
            )
            SearchScreen(
                onBackClickAction = {navController.popBackStack()},
                viewModel = viewModel,
                onTrackClick = { track ->
                    val json = gson.toJson(track)
                    val encodedJson = URLEncoder.encode(json, StandardCharsets.UTF_8.toString())
                    navController.navigate("${ScreenEnum.TrackDetailsScreen.name}/$encodedJson")
                }
            )
        }
        composable(
            route = ScreenEnum.PlaylistsScreen.name
        ) {
            PlaylistsScreen(
                playlistsViewModel = playlistsViewModel,
                addNewPlaylist = { navController.navigate(ScreenEnum.CreatePlaylistScreen.name) },
                navigateBack = { navController.popBackStack() },
                navigateToPlaylist = { id -> navController.navigate("${ScreenEnum.PlaylistDetailsScreen.name}/$id") }
            )
        }
        composable(
            route = ScreenEnum.CreatePlaylistScreen.name
        ) {
            CreatePlaylistScreen(
                viewModel = playlistsViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(
            route = ScreenEnum.FavoritesScreen.name
        ) {
            FavoritesScreen(
                viewModel = playlistsViewModel,
                onBackClick = { navController.popBackStack() },
                onTrackClick = { track ->
                    val json = gson.toJson(track)
                    val encodedJson = URLEncoder.encode(json, StandardCharsets.UTF_8.toString())
                    navController.navigate("${ScreenEnum.TrackDetailsScreen.name}/$encodedJson")
                }
            )
        }
        composable(
            route = "${ScreenEnum.TrackDetailsScreen.name}/{trackJson}"
        ) { backStackEntry ->
            val trackJson = backStackEntry.arguments?.getString("trackJson")
            val decodedJson = URLDecoder.decode(trackJson, StandardCharsets.UTF_8.toString())
            val track = gson.fromJson(decodedJson, Track::class.java)
            
            TrackDetailsScreen(
                track = track,
                viewModel = playlistsViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(
            route = "${ScreenEnum.PlaylistDetailsScreen.name}/{playlistId}",
            arguments = listOf(navArgument("playlistId") { type = NavType.LongType })
        ) { backStackEntry ->
            val playlistId = backStackEntry.arguments?.getLong("playlistId") ?: 0L
            val viewModel: PlaylistViewModel = viewModel(
                factory = PlaylistViewModel.getViewModelFactory(playlistId)
            )
            PlaylistScreen(
                playlistViewModel = viewModel,
                onBackClick = { navController.popBackStack() },
                onTrackClick = { track ->
                    val json = gson.toJson(track)
                    val encodedJson = URLEncoder.encode(json, StandardCharsets.UTF_8.toString())
                    navController.navigate("${ScreenEnum.TrackDetailsScreen.name}/$encodedJson")
                }
            )
        }
    }
}
