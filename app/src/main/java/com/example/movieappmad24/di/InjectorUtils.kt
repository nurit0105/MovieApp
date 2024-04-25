package com.example.movieappmad24.di

import com.example.movieappmad24.repositories.MovieRepository
import android.content.Context
import android.util.Log
import com.example.movieappmad24.MovieViewModelFactory
import com.example.movieappmad24.data.MovieDB
object InjectorUtils {
    fun getMovieRepository(context: Context): MovieRepository {
        val database = MovieDB.getDB(context.applicationContext)
        Log.d("InjectorUtils", "Getting MovieRepository instance")
        return MovieRepository.getInstance(database.movieDao())
    }

    fun provideMovieViewModelFactory(context: Context): MovieViewModelFactory {
        val repository = getMovieRepository(context)
        Log.d("InjectorUtils", "Providing MovieViewModelFactory")
        return MovieViewModelFactory(repository = repository)
    }

    fun getMovieDB(context: Context): MovieDB {
        Log.d("InjectorUtils", "Getting MovieDB instance")
        return MovieDB.getDB(context)
    }
}