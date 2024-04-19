package com.example.movieappmad24.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface MovieDao {
    @Insert
    suspend fun add(movie: Movie)

    @Update
    suspend fun update(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)
    @Query("SELECT * FROM movie")
    fun readAll(): Flow<List<Movie>>

    @Query("SELECT * from movie where id=:movieId")
    fun getMovieById(movieId: Int): Movie
    @Insert
    suspend fun addMovies(movies: List<Movie>)

    @Query("SELECT COUNT(*) FROM movie")
    suspend fun countMovies(): Int
}