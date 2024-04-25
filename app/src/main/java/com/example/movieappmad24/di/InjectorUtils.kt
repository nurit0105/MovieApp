package com.example.movieappmad24.di

import android.content.Context
import com.example.movieappmad24.DetailScreenViewModelFactory
import com.example.movieappmad24.HomeScreenViewModelFactory
import com.example.movieappmad24.WatchlistScreenViewModelFactory
import com.example.movieappmad24.data.MovieDB
import com.example.movieappmad24.repositories.MovieRepository


object InjectorUtils {

    private fun getMovieRepository(context: Context): MovieRepository {
        val db = MovieDB.getDB(context)
        return MovieRepository.getInstance(db.movieDao())
    }

    fun provideHomeScreenViewModelFactory(context: Context): HomeScreenViewModelFactory {
        val repository = getMovieRepository(context)
        return HomeScreenViewModelFactory(repository)
    }

    fun provideWatchlistScreenViewModelFactory(context: Context): WatchlistScreenViewModelFactory {
        val repository = getMovieRepository(context)
        return WatchlistScreenViewModelFactory(repository)
    }

    fun provideDetailScreenViewModelFactory(context: Context, movieId: Int): DetailScreenViewModelFactory {
        val repository = getMovieRepository(context)
        return DetailScreenViewModelFactory(repository, movieId)
    }
}