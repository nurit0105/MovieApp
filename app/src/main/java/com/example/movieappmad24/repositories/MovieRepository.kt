package com.example.movieappmad24.repositories

import android.util.Log
import com.example.movieappmad24.data.MovieDao
import com.example.movieappmad24.data.MovieWithImages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class MovieRepository(private val movieDao: MovieDao) {

    suspend fun getAllMoviesWithImages(): Flow<List<MovieWithImages>> {
        return flow {
            val moviesWithImages = movieDao.getAllMoviesWithImages()
            Log.d("MovieRepository", "Fetched ${moviesWithImages.size} movies with images")
            emit(moviesWithImages)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun update(movieWithImages: MovieWithImages) {
        withContext(Dispatchers.IO) {
            movieDao.updateMovie(movieWithImages.movie)
            movieWithImages.images.forEach { movieImage ->
                movieDao.updateMovieImage(movieImage)
            }
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

