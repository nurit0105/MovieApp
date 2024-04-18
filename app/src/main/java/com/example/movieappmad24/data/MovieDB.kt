package com.example.movieappmad24.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Movie::class],
    version = 2, //Don't forget to update when you change your "schema" -> your Lists/Tables/DB
    exportSchema = false
)

abstract class MovieDB: RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        /* TODO:
        *   Singelton Pattern nochmal nachschauen "is thread safe"
        *   google Multithreading and threading in general
        *   google what synchronized actually means for dummies */

        @Volatile
        private var Instance: MovieDB? = null //

        fun getDB(context: Context): MovieDB{
            return Instance?: synchronized(this){
                Room.databaseBuilder(context, MovieDB::class.java, "movie_db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        Instance = it
                    }
            }
        }
    }
}