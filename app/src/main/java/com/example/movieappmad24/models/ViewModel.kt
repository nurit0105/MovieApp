package com.example.movieappmad24.models

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

// Inherit from ViewModel class
class MoviesViewModel : ViewModel() {
    private val _movies = getMovies().toMutableStateList()   // get all movies and create a StateHolder from it, so it can be observed by UI
    val movies: List<Movie>  //  expose previously created list but immutable
        get() = _movies

    val favoriteMovies: List<Movie>
        get() = _movies.filter { movie -> movie.isFavorite }

    fun toggleFavoriteMovie(movieId: String) = _movies.find { it.id == movieId }?.let { movie ->
        movie.isFavorite = !movie.isFavorite
    }


}