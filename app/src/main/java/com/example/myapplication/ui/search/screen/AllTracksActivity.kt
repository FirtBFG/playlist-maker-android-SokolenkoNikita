package com.example.myapplication.ui.search.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.search.screen.ui.theme.PlaylistmakerandroidTheme
import com.example.myapplication.ui.search.viewModel.SearchViewModel

//class AllTracksActivity : ComponentActivity() {
//    private val viewModel by viewModels<SearchViewModel> {
//        SearchViewModel.getViewModelFactory()
//    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            PlaylistmakerandroidTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    AllTracksScreen(
//                        modifier = Modifier.padding(innerPadding),
//                        viewModel = viewModel
//                    )
//                }
//            }
//        }
//    }
//}