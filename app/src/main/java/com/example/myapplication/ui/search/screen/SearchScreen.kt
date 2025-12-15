package com.example.myapplication.ui.search.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.History
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.domain.models.Track
import com.example.myapplication.ui.components.PanelHeader
import com.example.myapplication.ui.search.components.EmptySearchBox
import com.example.myapplication.ui.search.components.PanelSearch
import com.example.myapplication.ui.search.components.SearchErrorScreen
import com.example.myapplication.ui.search.components.TrackList
import com.example.myapplication.ui.search.state.SearchState
import com.example.myapplication.ui.search.viewModel.SearchViewModel
import com.example.myapplication.ui.theme.YPLightGray

@Composable
fun SearchScreen(
    onBackClickAction: () -> Unit,
    viewModel: SearchViewModel,
    onTrackClick: (Track) -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }
    val screenState by viewModel.searchScreenState.collectAsState()
    
    LaunchedEffect(Unit) {
        viewModel.clearState()
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            PanelHeader(
                title = stringResource(R.string.title_activity_search),
                onClickAction = onBackClickAction
            )
        },
    ) { innerPadding ->
        Box(Modifier.fillMaxSize().padding(innerPadding)) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .padding(top = 8.dp)
                ) {
                    val history by viewModel.searchHistory.collectAsState()
                    val showHistory = searchQuery.isEmpty() && history.isNotEmpty()
                    val searchBoxShape = if (showHistory) {
                        RoundedCornerShape(
                            topStart = 8.dp,
                            topEnd = 8.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp
                        )
                    } else {
                        RoundedCornerShape(8.dp)
                    }
                    PanelSearch(
                        searchText = searchQuery,
                        onValueChange = { searchQuery = it },
                        onLendingIconClickAction = { viewModel.search(searchQuery) },
                        onTrailingIconClickAction = {
                            searchQuery = ""
                            viewModel.clearState()
                        },
                        modifier = Modifier.clip(searchBoxShape)
                    )

                    when (screenState) {
                        is SearchState.Initial -> {
                            if (showHistory) {
                                Column(
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    HorizontalDivider(
                                        color = Color(0xFFB5B5B6),
                                        thickness = 1.dp,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(
                                                color = YPLightGray,
                                                shape = RoundedCornerShape(
                                                    topStart = 0.dp,
                                                    topEnd = 0.dp,
                                                    bottomStart = 8.dp,
                                                    bottomEnd = 8.dp
                                                )
                                            )
                                            .padding(vertical = 8.dp)
                                    ) {
                                        Column(
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            history.forEach { query ->
                                                Row(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .clickable {
                                                            searchQuery = query
                                                            viewModel.search(query)
                                                        }
                                                        .padding(vertical = 12.dp, horizontal = 16.dp),
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Outlined.History,
                                                        contentDescription = null,
                                                        tint = Color(0xFFB5B5B6),
                                                        modifier = Modifier.size(20.dp)
                                                    )
                                                    Spacer(modifier = Modifier.width(12.dp))
                                                    Text(
                                                        text = query,
                                                        fontSize = 16.sp,
                                                        color = Color(0xFF1A1B22)
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        is SearchState.Searching -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 24.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }

                        is SearchState.Success -> {
                            val tracks = (screenState as SearchState.Success).foundList
                            if (tracks.isEmpty()) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(top = 12.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    EmptySearchBox()
                                }
                            } else {
                                TrackList(
                                    tracks = tracks,
                                    onTrackClick = { onTrackClick(it) }
                                )
                            }
                        }

                        is SearchState.Error -> {
                            SearchErrorScreen(
                                error = (screenState as SearchState.Error).error,
                                onRetry = { viewModel.retryLastFailed() }
                            )
                        }
                    }
                }
            }
        }
    }
}
