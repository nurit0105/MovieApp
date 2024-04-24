package com.example.movieappmad24.models

import android.util.Log
import com.example.movieappmad24.repositories.MovieRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad24.data.MovieWithImages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MoviesViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movies = MutableStateFlow<List<MovieWithImages>>(emptyList())
    val movies: StateFlow<List<MovieWithImages>> = _movies

    private val _favoriteMovies = MutableStateFlow<List<MovieWithImages>>(emptyList())
    val favoriteMovies: StateFlow<List<MovieWithImages>> = _favoriteMovies

    init {
        viewModelScope.launch {
            repository.getAllMoviesWithImages().collectLatest { movieList ->
                Log.d("MoviesViewModel", "Received ${movieList.size} movies with images")
                _movies.value = movieList
                _favoriteMovies.value = movieList.filter { it.movie.isFavorite }
            }
        }
    }

    fun toggleFavoriteMovie(movieWithImages: MovieWithImages) {
        viewModelScope.launch(Dispatchers.IO) {
            movieWithImages.movie.isFavorite = !movieWithImages.movie.isFavorite

            try {
                repository.update(movieWithImages)

                val updatedMovies = movies.value.map { m ->
                    if (m.movie.id == movieWithImages.movie.id) movieWithImages else m
                }
                _movies.value = updatedMovies
                _favoriteMovies.value = updatedMovies.filter { it.movie.isFavorite }
            } catch (e: Exception) {
                Log.e("MoviesViewModel", "Error updating movie with images", e)
            }
        }
    }
}

