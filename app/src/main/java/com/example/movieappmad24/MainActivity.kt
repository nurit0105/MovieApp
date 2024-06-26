package com.example.movieappmad24

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppMAD24Theme {
                val navController = rememberNavController() // here I set up the NavController
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                  AppNavigation(navController = navController) //The Navigation goes through the different screens that the MainActivity calls
                }
            }
        }
    }
}

// TODO: ask Leon or René why the isFavoriteIcon is behaving so damn weird with the Switch between the Screens