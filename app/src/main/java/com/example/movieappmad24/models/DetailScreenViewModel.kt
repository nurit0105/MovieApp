package com.example.movieappmad24.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad24.data.MovieWithImages
import com.example.movieappmad24.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailScreenViewModel(private val repository: MovieRepository, private val movieId: Int) : ViewModel() {
    private val _movies = MutableStateFlow<MovieWithImages?>(null)
    val movies: StateFlow<MovieWithImages?> = _movies

    init {
        viewModelScope.launch {
            repository.getAllMoviesWithImages().collectLatest { movieList ->
                val movieWithImages = movieList.find { it.movie.id == movieId }
                _movies.value = movieWithImages
            }
        }
    }

    fun toggleFavorite() {
        val movieWithImages = _movies.value
        if (movieWithImages != null) {
            viewModelScope.launch {
                movieWithImages.movie.isFavorite = !movieWithImages.movie.isFavorite

                repository.update(movieWithImages)
                _movies.value = movieWithImages
            }
        }
    }
}