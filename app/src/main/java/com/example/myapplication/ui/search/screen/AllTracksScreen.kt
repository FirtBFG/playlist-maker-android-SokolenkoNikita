package com.example.myapplication.ui.search.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextFieldDefaults.contentPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.myapplication.ui.search.viewModel.SearchViewModel
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.search.components.TrackListItem
import com.example.myapplication.ui.search.state.SearchState

//@Composable
//fun AllTracksScreen(modifier: Modifier, viewModel: SearchViewModel) {
//    val screenState by viewModel.allTracksScreenState.collectAsState()
//
//    LaunchedEffect(key1 = true) {
//        viewModel.fetchData()
//    }
//
//    when (screenState) {
//        is SearchState.Initial -> {}
//        is SearchState.Loading -> {
//            Box(
//                modifier = Modifier.fillMaxSize(),
//                contentAlignment = Alignment.Center,
//            ) {
//                CircularProgressIndicator()
//            }
//        }
//        is SearchState.Success -> {
//            val tracks = (screenState as SearchState.Success).foundList
//            LazyColumn(
//                modifier = Modifier.fillMaxSize(),
//                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
//                verticalArrangement = Arrangement.spacedBy(12.dp)
//            ) {
//                items(tracks.size) { index ->
//                    TrackListItem(track = tracks[index])
//                    HorizontalDivider(thickness = 0.5.dp)
//                }
//            }
//        }
//        is SearchState.Error -> {
//            val error = (screenState as SearchState.Error).error
//            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                Text("Ошибка: $error", color = Color.Red)
//            }
//        }
//    }
//}