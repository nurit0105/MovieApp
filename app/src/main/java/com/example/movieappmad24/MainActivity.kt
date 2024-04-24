package com.example.movieappmad24

import com.example.movieappmad24.models.MoviesViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.movieappmad24.data.MovieDB
import com.example.movieappmad24.data.MovieWithImages
import com.example.movieappmad24.data.getMovieImage
import com.example.movieappmad24.data.getMovies
import com.example.movieappmad24.di.InjectorUtils
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppMAD24Theme {
                val context = LocalContext.current
                val movieDB = MovieDB.getDB(context)
                val moviesViewModel: MoviesViewModel =
                    viewModel(factory = InjectorUtils.provideMovieViewModelFactory(context))
                val navController = rememberNavController()

                val coroutineScope = rememberCoroutineScope()

                // Add data initialization step
                LaunchedEffect(Unit) {
                    coroutineScope.launch(Dispatchers.IO) {
                        val movieRepository = InjectorUtils.getMovieRepository(context)

                        val initialMovies = getMovies()
                        val initialMovieImages = getMovieImage()

                        initialMovies.forEach { movie ->
                            movieRepository.addMovieWithImages(
                                MovieWithImages(
                                    movie = movie,
                                    images = initialMovieImages.filter { it.movieId == movie.id }
                                )
                            )
                        }
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
