package com.example.movieappmad24.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

@Database(
    entities = [Movie::class, MovieImage::class],
    version = 9,
    exportSchema = false
)
abstract class MovieDB : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    fun populateDatabase(context: Context) {
        val dao = movieDao()

        val initialMovies = getMovies()
        val initialMovieImages = getMovieImage()

        runBlocking(Dispatchers.IO) {
            initialMovies.forEach { movie ->
                val exists = dao.movieExists(movie.title) > 0

                if (!exists) {
                    dao.addMovie(movie)
                }
            }

            initialMovieImages.forEach { movieImage ->
               val existImages = dao.movieImageExists(movieImage.movieId, movieImage.url)
                if (existImages == 0) {
                    dao.addMovieImage(movieImage)
                }
            }
        }
    }

    companion object {
        @Volatile
        private var instance: MovieDB? = null

        fun getDB(context: Context): MovieDB {
            val db = instance ?: synchronized(this) {
                Room.databaseBuilder(context, MovieDB::class.java, "movie_db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { instance = it }
            }

            db.populateDatabase(context)

            return db
        }
    }
}