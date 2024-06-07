package com.example.movieappmad24.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.util.TableInfo

@Entity(tableName = "movie", indices = [Index(value = ["orTitle"], unique = true)])
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val orTitle: String,
    val dtTitle: String?,
    var lengthMin: Int,
    var year: Int,
    var country: String,
    var production: String,
    var director: String,
    var book: String?,
    var camera: String?,
    var music: String?,
    var actor1: String?,
    var actor2: String?,
    var actor3: String?,
    var imageUri: String? = null,
    var isFavorite: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}

fun getMovies(): List<Movie> {
    return listOf(
    )
}