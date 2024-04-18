package com.example.movieappmad24

import MoviesViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieappmad24.repositories.MovieRepository

//TODO: google Factory Pattern
class MovieViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                MoviesViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
