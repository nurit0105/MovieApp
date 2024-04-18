package com.example.movieappmad24.screen

import ListOfVisibleObjectGroups
import MoviesViewModel
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieappmad24.widgets.SimpleBottomAppBar
import com.example.movieappmad24.widgets.SimpleTopAppBar

@Composable
fun WatchlistScreen(navController: NavController, moviesViewModel: MoviesViewModel) {
    val favoriteMovies by moviesViewModel.favoriteMovies.collectAsState() // Collects state from StateFlow

    Scaffold(
        topBar = {
            SimpleTopAppBar(title = "Your Watchlist")
        },
        bottomBar = {
            SimpleBottomAppBar(navController = navController)
        }
    ) { innerPadding ->
        ListOfVisibleObjectGroups(
            modifier = Modifier.padding(innerPadding),
            movies = favoriteMovies, // Passing the collected state
            navController = navController,
            viewModel = moviesViewModel
        )
    }
}
