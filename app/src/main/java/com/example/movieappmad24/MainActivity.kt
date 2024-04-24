package com.example.movieappmad24

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.movieappmad24.di.InjectorUtils
import com.example.movieappmad24.models.MoviesViewModel
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppMAD24Theme {
                val context = LocalContext.current
                val movieRepository = InjectorUtils.getMovieRepository(context)

                val moviesViewModel: MoviesViewModel = viewModel(factory = InjectorUtils.provideMovieViewModelFactory(context))
                val navController = rememberNavController()

                LaunchedEffect(Unit) {
                    val movieCount = movieRepository.getCountMovies()
                    if (movieCount == 0) {
                        val movieDB = InjectorUtils.getMovieDB(context)
                        movieDB.populateDatabase(context)
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(navController, moviesViewModel)
                }
            }
        }
    }
}
