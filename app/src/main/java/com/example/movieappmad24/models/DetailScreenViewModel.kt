package com.example.movieappmad24.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad24.data.Movie
import com.example.movieappmad24.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailScreenViewModel(private val repository: MovieRepository, private val movieId: Int) : ViewModel() {
    private val _movies = MutableStateFlow<Movie?>(null)
    val movies: StateFlow<Movie?> = _movies

    init {
        viewModelScope.launch {
            repository.getAllMovies().collectLatest { movieList ->
                val movie = movieList.find { it.id == movieId }
                _movies.value = movie
            }
        }
    }

    fun toggleFavorite() {
        val movie = _movies.value
        if (movie != null) {
            viewModelScope.launch {
                movie.isFavorite = !movie.isFavorite

                repository.update(movie)
                _movies.value = movie
            }
        }
    }
}