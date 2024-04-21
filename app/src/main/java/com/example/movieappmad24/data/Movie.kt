package com.example.movieappmad24.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieappmad24.R

@Entity(tableName = "movie")
class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "year")
    val year: String,
    @ColumnInfo(name = "genre")
    val genre: String,
    @ColumnInfo(name = "director")
    val director: String,
    @ColumnInfo(name = "actors")
    val actors: String,
    @ColumnInfo(name = "plot")
    val plot: String,
    @ColumnInfo(name = "trailer")
    val trailer: Int,
    @ColumnInfo(name = "rating")
    val rating: String,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}

fun getMovies(): List<Movie> {
    return listOf(
        Movie(
            title = "Avatar",
            year = "2009",
            genre = "Action, Adventure, Fantasy",
            director = "James Cameron",
            actors = "Sam Worthington, Zoe Saldana, Sigourney Weaver, Stephen Lang",
            plot = "A paraplegic marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.",
            trailer = R.raw.trailer_placeholder,
            rating = "7.9"
        ),

        Movie(
            title = "300",
            year = "2006",
            genre = "Action, Drama, Fantasy",
            director = "Zack Snyder",
            actors = "Gerard Butler, Lena Headey, Dominic West, David Wenham",
            plot = "King Leonidas of Sparta and a force of 300 men fight the Persians at Thermopylae in 480 B.C.",
            trailer = R.raw.trailer_placeholder,
            rating = "7.7"
        ),

        Movie(
            title = "The Avengers",
            year = "2012",
            genre = "Action, Sci-Fi, Thriller",
            director = "Joss Whedon",
            actors = "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
            plot = "Earth's mightiest heroes must come together and learn to fight as a team if they are to stop the mischievous Loki and his alien army from enslaving humanity.",
            trailer = R.raw.trailer_placeholder,
            rating = "8.1"
        ),

        Movie(
            title = "The Wolf of Wall Street",
            year = "2013",
            genre = "Biography, Comedy, Crime",
            director = "Martin Scorsese",
            actors = "Leonardo DiCaprio, Jonah Hill, Margot Robbie, Matthew McConaughey",
            plot = "Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government.",
            /* images = listOf(",



                "),*/
            trailer = R.raw.trailer_placeholder,
            rating = "8.2"
        ),

        Movie(
            title = "Interstellar",
            year = "2014",
            genre = "Adventure, Drama, Sci-Fi",
            director = "Christopher Nolan",
            actors = "Ellen Burstyn, Matthew McConaughey, Mackenzie Foy, John Lithgow",
            plot = "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.",
            trailer = R.raw.trailer_placeholder,
            rating = "8.6"
        ),
        Movie(
            title = "Game of Thrones",
            year = "2011 - 2018",
            genre = "Adventure, Drama, Fantasy",
            director = "N/A",
            actors = "Peter Dinklage, Lena Headey, Emilia Clarke, Kit Harington",
            plot = "While a civil war brews between several noble families in Westeros, the children of the former rulers of the land attempt to rise up to power. Meanwhile a forgotten race, bent on destruction, plans to return after thousands of years in the North.",
            trailer = R.raw.trailer_placeholder,
            rating = "9.5"
        ),


        Movie(
            title = "Vikings",
            year = "2013–2020",
            genre = "Action, Drama, History",
            director = "N/A",
            actors = "Travis Fimmel, Clive Standen, Gustaf Skarsgård, Katheryn Winnick",
            plot = "The world of the Vikings is brought to life through the journey of Ragnar Lothbrok, the first Viking to emerge from Norse legend and onto the pages of history - a man on the edge of myth.",
            trailer = R.raw.trailer_placeholder,
            rating = "9.5"
        ),

        Movie(
            title = "Breaking Bad",
            year = "2008–2013",
            genre = "Crime, Drama, Thriller",
            director = "N/A",
            actors = "Bryan Cranston, Anna Gunn, Aaron Paul, Dean Norris",
            plot = "A high school chemistry teacher diagnosed with inoperable lung cancer turns to manufacturing and selling methamphetamine in order to secure his family's financial future.",
            trailer = R.raw.trailer_placeholder,
            rating = "9.5"
        ),

        Movie(
            title = "Narcos",
            year = "2015-",
            genre = "Biography, Crime, Drama",
            director = "N/A",
            actors = "Wagner Moura, Boyd Holbrook, Pedro Pascal, Joanna Christie",
            plot = "A chronicled look at the criminal exploits of Colombian drug lord Pablo Escobar.",
            trailer = R.raw.trailer_placeholder,
            rating = "9.5"
        ),
    )
}