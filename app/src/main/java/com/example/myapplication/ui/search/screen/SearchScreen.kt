package com.example.myapplication.ui.search.screen

import com.example.myapplication.ui.components.PanelHeader
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.PlaylistmakerandroidTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import com.example.myapplication.R
import com.example.myapplication.domain.models.Track
import com.example.myapplication.ui.search.components.EmptySearchBox
import com.example.myapplication.ui.search.components.ErrorSearchBox
import com.example.myapplication.ui.search.components.InitialSearchBox
import com.example.myapplication.ui.search.components.PanelSearch
import com.example.myapplication.ui.search.components.TrackList
import com.example.myapplication.ui.search.state.SearchState
import com.example.myapplication.ui.search.viewModel.SearchViewModel


@Composable
fun SearchScreen(
    onBackClickAction: () -> Unit,
    viewModel: SearchViewModel,
    onTrackClick: (Track) -> Unit = {}
) {
    val screenState by viewModel.searchScreenState.collectAsState()
    var searchText by rememberSaveable { mutableStateOf("") }
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            PanelHeader(
                title = stringResource(id = R.string.title_activity_search),
                onClickAction = onBackClickAction
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            PanelSearch(
                searchText,
                onValueChange = { value -> searchText = value },
                onTrailingIconClickAction = { searchText = "" },
                onLendingIconClickAction = { viewModel.search(searchText) }
            )
            when (screenState) {
                is SearchState.Initial -> {
                    InitialSearchBox()
                }
                is SearchState.Searching -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is SearchState.Success -> {
                    val tracks = (screenState as SearchState.Success).foundList
                    TrackList(tracks = tracks, onTrackClick = onTrackClick)
                }
                is SearchState.EmptyList -> {
                    EmptySearchBox()
                }
                is SearchState.Error -> {
                    val error = (screenState as SearchState.Error).error
                    ErrorSearchBox(error)
                }
            }
        }
    }
}
