package com.example.movieappmad24.screen

import com.example.movieappmad24.models.MoviesViewModel
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieappmad24.widgets.SimpleBottomAppBar
import com.example.movieappmad24.widgets.SimpleTopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import com.example.movieappmad24.widgets.CoilImage
import com.example.movieappmad24.widgets.SingleVisibleObjectGroup
import com.example.movieappmad24.widgets.Trailer

@Composable
fun DetailScreen(
    movieId: Int,
    navController: NavController,
    moviesViewModel: MoviesViewModel
) {
    val movies by moviesViewModel.movies.collectAsState()

    val currentMovie by remember {
        mutableStateOf(movies.find { it.movie.id == movieId })
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back Icon")
            }
            SimpleTopAppBar(title = currentMovie?.movie?.title ?: "Movie Details")
        },
        bottomBar = {
            SimpleBottomAppBar(navController = navController)
        },
        content = { innerPadding ->
            currentMovie?.let { movieWithImages ->
                LazyColumn(modifier = Modifier.padding(innerPadding)) {
                    item {
                        SingleVisibleObjectGroup(
                            movieWithImages = movieWithImages,
                            onFavoriteClick = { moviesViewModel.toggleFavoriteMovie(movieWithImages) }
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Trailer(movieWithImages = movieWithImages)
                        CoilImage(movieWithImages = movieWithImages)
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }
            }
        }
    )
}



