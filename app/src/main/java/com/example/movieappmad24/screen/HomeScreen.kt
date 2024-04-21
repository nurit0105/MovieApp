package com.example.movieappmad24.screen

import com.example.movieappmad24.models.MoviesViewModel
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieappmad24.widgets.ListOfVisibleObjectGroups
import com.example.movieappmad24.widgets.SimpleBottomAppBar
import com.example.movieappmad24.widgets.SimpleTopAppBar


@Composable
fun HomeScreen(navController: NavController, moviesViewModel: MoviesViewModel) {
    val movies by moviesViewModel.movies.collectAsState()

    Scaffold(
        topBar = {
            SimpleTopAppBar(title = "Movie App")
        },
        bottomBar = {
            SimpleBottomAppBar(navController = navController)
        }
    ) { innerPadding ->
        ListOfVisibleObjectGroups(
            modifier = Modifier.padding(innerPadding),
            movies = movies,
            navController = navController,
            viewModel = moviesViewModel
        )
    }
}

