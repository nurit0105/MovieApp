package com.example.movieappmad24.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Movie::class, MovieImage::class],
    version = 6,
    exportSchema = false
)
abstract class MovieDB : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var instance: MovieDB? = null

        fun getDB(context: Context): MovieDB {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context, MovieDB::class.java, "movie_db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { instance = it }
            }
        }
    }
}


/* TODO:
       *   Singelton Pattern nochmal nachschauen "is thread safe"
       *   google Multithreading and threading in general
       *   google what synchronized actually means for dummies
 */