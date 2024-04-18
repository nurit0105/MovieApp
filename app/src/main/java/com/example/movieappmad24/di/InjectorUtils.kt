package com.example.movieappmad24.di

import android.content.Context
import com.example.movieappmad24.MovieViewModelFactory
import com.example.movieappmad24.data.MovieDB
import com.example.movieappmad24.repositories.MovieRepository

object InjectorUtils {

    private fun getMovieRepository(context: Context): MovieRepository {
        return MovieRepository.getInstance(MovieDB.getDB(context.applicationContext).movieDao())
    }

    fun provideMovieViewModelFactory(context: Context): MovieViewModelFactory {
        val repository = getMovieRepository(context)
        return MovieViewModelFactory(repository = repository)
    }
}