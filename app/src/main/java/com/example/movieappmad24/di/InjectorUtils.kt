package com.example.movieappmad24.di

import com.example.movieappmad24.repositories.MovieRepository
import android.content.Context
import com.example.movieappmad24.MovieViewModelFactory
import com.example.movieappmad24.data.MovieDB
object InjectorUtils {
    fun getMovieRepository(context: Context): MovieRepository {
        val database = MovieDB.getDB(context.applicationContext)
        return MovieRepository.getInstance(database.movieDao())
    }

    fun provideMovieViewModelFactory(context: Context): MovieViewModelFactory {
        val repository = getMovieRepository(context)
        return MovieViewModelFactory(repository = repository)
    }

    fun getMovieDB(context: Context): MovieDB {
        return MovieDB.getDB(context)
    }
}