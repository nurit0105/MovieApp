package com.example.movieappmad24

import com.example.movieappmad24.repositories.MovieRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieappmad24.models.AddMovieViewModel
import com.example.movieappmad24.models.DetailScreenViewModel
import com.example.movieappmad24.models.HomeScreenViewModel
import com.example.movieappmad24.models.WatchlistScreenViewModel

// TODO: google Factory Pattern

class HomeScreenViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeScreenViewModel::class.java) -> {
                HomeScreenViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

class AddMovieViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AddMovieViewModel::class.java) -> {
                AddMovieViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

class WatchlistScreenViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(WatchlistScreenViewModel::class.java) -> {
                WatchlistScreenViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

class DetailScreenViewModelFactory(private val repository: MovieRepository, private val movieId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DetailScreenViewModel::class.java) -> {
                DetailScreenViewModel(repository, movieId) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
