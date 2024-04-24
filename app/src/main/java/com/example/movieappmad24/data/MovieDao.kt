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

    @Insert
    suspend fun addMovieImage(movieImage: MovieImage)

    @Update
    suspend fun updateMovieImage(movieImage: MovieImage)

    @Delete
    suspend fun deleteMovieImage(movieImage: MovieImage)

    @Transaction
    @Query("SELECT * FROM movie")
    fun getAllMoviesWithImages(): List<MovieWithImages>

    @Transaction
    @Query("SELECT * FROM movie WHERE id = :movieId")
    fun getMovieWithImages(movieId: Int): MovieWithImages
    @Transaction
    @Query("SELECT * FROM movie")
    fun allMoviesWithImages(): List<MovieWithImages>

    @Query("SELECT COUNT(*) FROM movie")
    suspend fun countMovies(): Int

    @Query("SELECT COUNT(*) FROM movie WHERE title = :title")
    suspend fun movieExists(title: String): Int
}

