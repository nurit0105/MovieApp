package com.example.movieappmad24.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface MovieDao {
    @Insert
    suspend fun addMovie(movie: Movie)

    @Update
    suspend fun updateMovie(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)

    @Transaction
    @Query("SELECT * FROM movie")
    fun getAllMovies(): List<Movie>

    @Transaction
    @Query("SELECT * FROM movie WHERE id = :movieId")
    fun getMovie(movieId: Int): Movie

    @Query("SELECT COUNT(*) FROM movie")
    suspend fun countMovies(): Int

    @Query("SELECT COUNT(*) FROM movie WHERE orTitle = :title")
    suspend fun movieExists(title: String): Int
}

