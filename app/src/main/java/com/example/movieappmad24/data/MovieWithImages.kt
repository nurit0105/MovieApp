package com.example.movieappmad24.data

import androidx.room.Embedded
import androidx.room.Relation

class MovieWithImages(
    @Embedded val movie: Movie,
    @Relation(parentColumn = "id", entityColumn = "movieId")
    val images: List<MovieImage>
)
