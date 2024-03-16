package com.example.movieappmad24.Screens

import com.example.movieappmad24.models.ListOfVisibleObjectGroups
import com.example.movieappmad24.models.SingleMovieObjectGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieappmad24.models.Bottoms
import com.example.movieappmad24.models.Tops
import com.example.movieappmad24.models.getMovies


@Composable
fun WatchListScreen(
    navController: NavController
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Tops(title = "Your Watch List")
        },
        bottomBar = {
            Bottoms(navController = navController)
        },
    ) { innerPadding ->
        ListOfVisibleObjectGroups(
            modifier = Modifier.padding(innerPadding),
            items = getMovies()
        ) { movie ->
            SingleMovieObjectGroup(item = movie, onClick = { /* do nothing */})
        }
    }
}
