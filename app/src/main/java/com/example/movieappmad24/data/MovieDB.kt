package com.example.movieappmad24.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking


@Database(
    entities = [Movie::class],
    version = 13,
    exportSchema = false
)
abstract class MovieDB : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    fun populateDatabase() {
        val dao = movieDao()

        val initialMovies = getMovies()

        runBlocking(Dispatchers.IO) {
            initialMovies.forEach { movie ->
                val exists = dao.movieExists(movie.orTitle) > 0
                if (!exists) {
                    dao.addMovie(movie)
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

            db.populateDatabase()

            return db
        }
    }
}