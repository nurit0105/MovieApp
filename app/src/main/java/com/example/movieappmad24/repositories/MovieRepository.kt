package com.example.movieappmad24.repositories

import com.example.movieappmad24.data.MovieDao
import com.example.movieappmad24.data.Movie

class MovieRepository(private val movieDao: MovieDao) {
    suspend fun add(movie: Movie) = movieDao.add(movie)
    suspend fun delete(movie: Movie) = movieDao.delete(movie)
    suspend fun update(movie: Movie) = movieDao.update(movie)

    suspend fun addMovies(movies: List<Movie>) {
        movieDao.addMovies(movies)
    }

    suspend fun getAllMovies() = movieDao.readAll()

    suspend fun getCountMovies() = movieDao.countMovies()

    companion object {
        @Volatile
        private var Instance: MovieRepository? = null

        fun getInstance(dao: MovieDao) = Instance ?: synchronized(this) {
            Instance ?: MovieRepository(dao).also { Instance = it }
        }
    }
}