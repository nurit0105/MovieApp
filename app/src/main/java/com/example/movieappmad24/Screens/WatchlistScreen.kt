package com.example.movieappmad24.Screens

import android.util.Log
import com.example.movieappmad24.models.ListOfVisibleObjectGroups
import com.example.movieappmad24.models.SingleMovieObjectGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieappmad24.models.SimpleBottomAppBar
import com.example.movieappmad24.models.SimpleTopAppBar
import com.example.movieappmad24.models.getMovies

@Composable
fun WatchListScreen(
    navController: NavController
) {
    Log.d("MovieApp", "Navigated to WatchListScreen")
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SimpleTopAppBar(title = "Your Watch List")
        },
        bottomBar = {
            SimpleBottomAppBar(navController = navController)
        },
    ) { innerPadding ->
        ListOfVisibleObjectGroups(
            modifier = Modifier.padding(innerPadding),
            items = getMovies()
        ) { movie ->
            SingleMovieObjectGroup(item = movie, onClick = { movieId ->
                Log.d("MovieApp", "Clicked on movie with ID: $movieId")
                try {
                    navController.navigate("detail/$movieId")
                } catch (e: Exception) {
                    Log.e("MovieApp", "Error navigating to detail screen: ${e.message}")
                }
            })
        }
    }
}