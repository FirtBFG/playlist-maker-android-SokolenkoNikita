package com.example.myapplication.ui.search.screen

import PanelHeader
import android.R.attr.contentDescription
import android.R.attr.maxLines
import android.R.attr.top
import android.service.autofill.OnClickAction
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.PlaylistmakerandroidTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.YPLightGray
import com.example.myapplication.ui.theme.YPTextGray
import com.example.myapplication.R
import com.example.myapplication.ui.search.components.TrackListItem
import com.example.myapplication.ui.search.state.SearchState
import com.example.myapplication.ui.search.viewModel.SearchViewModel


@Composable
fun SearchScreen(
    onBackClickAction: () -> Unit,
    viewModel: SearchViewModel
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
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = stringResource(id = R.string.search_screen_initial_text),)
                    }
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
                    LazyColumn (
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(tracks.size) { index ->
                            TrackListItem(track = tracks[index])
                        }
                    }
                }
                is SearchState.EmptyList -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 120.dp)
                                .padding(top = 102.dp)
                                .size(120.dp),
                            painter = painterResource(R.drawable.ic_no_found),
                            contentDescription = stringResource(id = R.string.no_found_desc)
                        )
                        Spacer(modifier = Modifier.padding(top = 16.dp))
                        Text(
                            text = stringResource(id = R.string.no_found),
                            fontWeight = FontWeight.Bold,
                            fontSize = 19.sp,
                        )
                    }
                }
                is SearchState.Error -> {
                    val error = (screenState as SearchState.Error).error
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 120.dp)
                                .padding(top = 102.dp)
                                .size(120.dp),
                            painter = painterResource(R.drawable.ic_no_connection),
                            contentDescription = stringResource(R.string.no_connection_desc)
                        )
                        Spacer(modifier = Modifier.padding(top = 16.dp))
                        Text(
                            text = stringResource(R.string.error),
                            fontWeight = FontWeight.Bold,
                            fontSize = 19.sp,
                        )
                        Spacer(modifier = Modifier.padding(top = 16.dp))
                        Text(
                            text = error,
                            fontWeight = FontWeight.Bold,
                            fontSize = 19.sp,
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun PanelSearch(
    searchText: String,
    onValueChange: (value: String) -> Unit,
    onTrailingIconClickAction: () -> Unit,
    onLendingIconClickAction: () -> Unit
    ) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(color = YPLightGray),
            value = searchText,
            onValueChange = {value -> onValueChange(value)},
            placeholder = {
                Text(
                    stringResource(id = R.string.search),
                    color = YPTextGray,
                    fontSize = 16.sp
                )
            },
            leadingIcon = {
                Icon(
                    modifier = Modifier.clickable {
                        onLendingIconClickAction()
                    },
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = YPTextGray
                )
            },
            trailingIcon = {
                if (!searchText.isEmpty()) {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = null,
                        modifier = Modifier.clickable(onClick = {onTrailingIconClickAction()}),
                        tint = YPTextGray
                    )
                }
            },
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = YPLightGray,
                unfocusedContainerColor = YPLightGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            maxLines = 1,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PanelSeachPreview() {
    PlaylistmakerandroidTheme(darkTheme = false) {
        PanelSearch(
            onTrailingIconClickAction = {},
            onValueChange = {},
            onLendingIconClickAction = {},
            searchText = "aboba"
        )
    }
}