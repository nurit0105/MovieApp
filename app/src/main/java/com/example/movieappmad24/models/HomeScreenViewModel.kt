package com.example.movieappmad24.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad24.data.MovieWithImages
import com.example.movieappmad24.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movies = MutableStateFlow<List<MovieWithImages>>(emptyList())
    val movies: StateFlow<List<MovieWithImages>> = _movies

    init {
        viewModelScope.launch {
            repository.getAllMoviesWithImages().collectLatest { movieList ->
                _movies.value = movieList
            }
        }
    }


    fun toggleFavorite(movieWithImages: MovieWithImages) {
        viewModelScope.launch {
            movieWithImages.movie.isFavorite = !movieWithImages.movie.isFavorite

            repository.update(movieWithImages)

            val updatedMovies = _movies.value.map { movie ->
                if (movie.movie.id == movieWithImages.movie.id) movieWithImages else movie
            }
            _movies.value = updatedMovies
        }
    }
}
