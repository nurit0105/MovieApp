package com.example.movieappmad24.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad24.data.Movie
import com.example.movieappmad24.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AddMovieViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    init {
        viewModelScope.launch {
            repository.getAllMovies().collectLatest { movieList ->
                _movies.value = movieList
            }
        }
    }

    fun addMovie(movie: Movie) {
        viewModelScope.launch {
            repository.addMovie(movie)
        }
    }
}