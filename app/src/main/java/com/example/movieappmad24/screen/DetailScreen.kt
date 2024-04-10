package com.example.movieappmad24.screen

import SingleVisibleObjectGroup
import Trailer
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieappmad24.models.MoviesViewModel
import com.example.movieappmad24.widgets.SimpleBottomAppBar
import com.example.movieappmad24.widgets.CoilImage
import com.example.movieappmad24.widgets.SimpleTopAppBar
import com.example.movieappmad24.models.getMovies

@Composable
fun DetailScreen(
    movieId: String,
    navController: NavController,
    moviesViewModel: MoviesViewModel
) {
    val currentMovie = moviesViewModel.movies.find { it.id == movieId }

    Log.d("MovieApp", "Navigated to DetailScreen")
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back Icon")
            }
            SimpleTopAppBar(title = currentMovie?.title ?: "Movie Details")
        },
        bottomBar = {
            SimpleBottomAppBar(navController = navController)
        },
        content = { innerPadding ->
            currentMovie?.let { movie ->
                LazyColumn(modifier = Modifier.padding(innerPadding)) {
                    item {
                        SingleVisibleObjectGroup(
                            modifier = Modifier.padding(innerPadding),
                            movie = movie,
                            onFavoriteClick = { moviesViewModel.toggleFavoriteMovie(movie.id) }
                        )
                        Card(modifier = Modifier
                            .padding(20.dp)
                            .background(Color.Black, shape = RoundedCornerShape(16.dp))) {
                            Row(modifier = Modifier.padding(horizontal = 10.dp)) {
                                Trailer(movie = movie)
                            }
                        }
                        Spacer(modifier = Modifier.padding(5.dp))
                        CoilImage(getImages = { movie.images }, item = movie)
                        Spacer(modifier = Modifier.padding(5.dp))
                    }
                }
            }
        }
    )
}

