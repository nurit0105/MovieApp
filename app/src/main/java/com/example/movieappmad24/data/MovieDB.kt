package com.example.movieappmad24.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

@Database(
    entities = [Movie::class, MovieImage::class],
    version = 7,
    exportSchema = false
)
abstract class MovieDB : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    // Add a data population function
    fun populateDatabase(context: Context) {
        val dao = movieDao()

        // Get initial data
        val initialMovies = getMovies()
        val initialMovieImages = getMovieImage()

        // Use a coroutine to add data to the database
        runBlocking(Dispatchers.IO) {
            initialMovies.forEach { movie ->
                dao.addMovie(movie)
                initialMovieImages.filter { it.movieId == movie.id }.forEach { movieImage ->
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

            // Populate the database with data on first launch
            db.populateDatabase(context)

            return db
        }
    }
}


/* TODO:
       *   Singelton Pattern nochmal nachschauen "is thread safe"
       *   google Multithreading and threading in general
       *   google what synchronized actually means for dummies
 */