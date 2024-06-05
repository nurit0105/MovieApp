package com.example.movieappmad24.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad24.data.Movie
import com.example.movieappmad24.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class WatchlistScreenViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    init {
        viewModelScope.launch {
            repository.getAllMovies().collectLatest { movieList ->
                val favoriteList = movieList.filter { it.isFavorite }
                _movies.value = favoriteList
            }
        }
    }

    fun toggleFavoriteMovie(movie: Movie) {
        viewModelScope.launch {
            movie.isFavorite = !movie.isFavorite

            repository.update(movie)

            val updatedFavoriteMovies = _movies.value.map { movie ->
               movie
            }.filter { it.isFavorite }
            _movies.value = updatedFavoriteMovies
        }
    }
}