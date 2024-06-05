package com.example.movieappmad24.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
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
    var isFavorite: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}

fun getMovies(): List<Movie> {
    return listOf(
        Movie(
            orTitle = "Test",
            dtTitle = "Test",
            lengthMin = 50,
            year = 2024,
            country = "",
            production = "Testion",
            director = "Ms. Test",
            book = "The Test of Test",
            camera = "TestCam",
            music = "TestMic",
            actor1 = "TE",
            actor2 = "S",
            actor3 = "T"
        )
    )
}