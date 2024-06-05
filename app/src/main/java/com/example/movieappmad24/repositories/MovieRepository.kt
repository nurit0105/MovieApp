package com.example.movieappmad24.repositories

import android.util.Log
import com.example.movieappmad24.data.Movie
import com.example.movieappmad24.data.MovieDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class MovieRepository(private val movieDao: MovieDao) {

    suspend fun getAllMovies(): Flow<List<Movie>> {
        return flow {
            val moviesWithImages = movieDao.getAllMovies()
            Log.d("MovieRepository", "Fetched ${moviesWithImages.size} movies with images")
            emit(moviesWithImages)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun update(movie: Movie) {
        withContext(Dispatchers.IO) {
            movieDao.updateMovie(movie)
        }
    }

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(dao: MovieDao): MovieRepository {
            return instance ?: synchronized(this) {
                instance ?: MovieRepository(dao).also { instance = it }
            }
        }
    }
}

