package com.example.movieappmad24.Screens

import android.util.Log
import com.example.movieappmad24.models.SingleMovieObjectGroup
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
import com.example.movieappmad24.models.SimpleBottomAppBar
import com.example.movieappmad24.models.CoilImage
import com.example.movieappmad24.models.SimpleTopAppBar
import com.example.movieappmad24.models.getMovies

@Composable
fun DetailScreen(
    movieId: String,
    navController: NavController
) {
    val movies = getMovies()
    val currentMovie = movies.find { it.id == movieId }

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
                        Spacer(modifier = Modifier.height(8.dp))
                        SingleMovieObjectGroup(
                            item = movie,
                            onClick = { }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        CoilImage(getImages = { movie.images }, item = movie)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    )
}