package com.example.movieappmad24.Screens

import com.example.movieappmad24.models.SingleMovieObjectGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieappmad24.models.Bottoms
import com.example.movieappmad24.models.CoilImage
import com.example.movieappmad24.models.Tops
import com.example.movieappmad24.models.getMovies



@Composable
fun DetailScreen(
    movieId: String,
    navController: NavController
) {
    val movies = getMovies()
    val currentMovie = movies.find { it.id == movieId }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back Icon") //#TODO Add functionality navigation backstack
            Tops(title = currentMovie?.title ?: "Movie Details")
        },
        bottomBar = {
            Bottoms(navController = navController)
        },
        content = { innerPadding ->
            currentMovie?.let { movie ->
                Column() {
                    Row(modifier = Modifier.padding(innerPadding)) {
                        Spacer(modifier = Modifier.height(8.dp))
                        SingleMovieObjectGroup(
                            item = movie,
                            onClick = { }
                        )
                    }
                    Row() {
                        CoilImage(getImages = { it.images }, item = movie)
                    }
                }
            }
        }
    )
}





