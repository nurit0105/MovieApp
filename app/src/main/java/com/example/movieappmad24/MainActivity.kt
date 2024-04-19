package com.example.movieappmad24

import MoviesViewModel
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
import com.example.movieappmad24.data.MovieDB
import com.example.movieappmad24.di.InjectorUtils
import com.example.movieappmad24.data.getMovies
import com.example.movieappmad24.repositories.MovieRepository
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme
import kotlinx.coroutines.flow.firstOrNull

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppMAD24Theme {
                val context = LocalContext.current
                val moviesViewModel: MoviesViewModel = viewModel(factory = InjectorUtils.provideMovieViewModelFactory(context))

                LaunchedEffect(Unit) {
                    val movieRepository = MovieRepository.getInstance(MovieDB.getDB(context).movieDao())
                    val movieCount = movieRepository.getCountMovies()
                    if (movieCount == 0) {
                        movieRepository.addMovies(getMovies())
                    }
                }

                val navController = rememberNavController()
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
