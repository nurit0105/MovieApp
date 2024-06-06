package com.example.movieappmad24.widgets


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.movieappmad24.data.Movie
import com.example.movieappmad24.models.DetailScreenViewModel
import com.example.movieappmad24.models.HomeScreenViewModel
import com.example.movieappmad24.models.WatchlistScreenViewModel


@Composable
fun ListOfVisibleObjectGroups(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
    navController: NavController,
    viewModel: ViewModel
) {
    Log.d("ListOfVisibleObjectGroups", "Displaying ${movies.size} movies")

    LazyColumn(modifier = modifier) {
        try {
            items(movies) { movie ->
                SingleVisibleObjectGroup(
                    movie = movie, // Pass the correct movie object
                    onFavoriteClick = {
                        handleFavoriteClick(movie, viewModel) // Ensure handleFavoriteClick takes a single Movie
                    },
                    onItemClick = { movieId ->
                        navController.navigate("detail/$movieId")
                    }
                )
            }
        } catch (e: Exception) {
            Log.e("ListOfVisibleObjectGroups", "Error displaying movies: ${e.message}")
        }
    }
}


fun handleFavoriteClick(movie: Movie, viewModel: ViewModel) {
    when (viewModel) {
        is HomeScreenViewModel -> {
            viewModel.toggleFavorite(movie)
        }
        is WatchlistScreenViewModel -> {
            viewModel.toggleFavoriteMovie(movie)
        }

        is DetailScreenViewModel -> {
            viewModel.toggleFavorite()
        }

        else -> {
            Log.w("ListOfVisibleObjectGroups", "Unknown ViewModel type")
        }
    }
}

@Composable
fun SingleVisibleObjectGroup(
    modifier: Modifier = Modifier,
    movie: Movie,
    onFavoriteClick: (Movie) -> Unit = {},
    onItemClick: (Int) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 3.dp)
            .clickable {
                onItemClick(movie.id)
            }, shape = ShapeDefaults.Large, elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column {
            MovieCardHeader(movie = movie, onFavoriteClick = {
                onFavoriteClick(movie)
            })
        }
    }
}

@Composable
fun MovieCardHeader(
    movie: Movie,
    onFavoriteClick: () -> Unit
) {
    val isFavoriteState = remember { mutableStateOf(movie.isFavorite) }
    LaunchedEffect(movie.isFavorite) {
        isFavoriteState.value = movie.isFavorite
    }
    Box(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(), contentAlignment = Alignment.Center
    ) {
        Text(text = movie.orTitle, fontWeight = FontWeight.Bold)
        FavoriteIcon(
            isFavorite = isFavoriteState.value,
            onFavoriteClick = {
                onFavoriteClick()
                isFavoriteState.value = !isFavoriteState.value
            }
        )
    }
}

 @Composable
fun FavoriteIcon(
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp), contentAlignment = Alignment.TopEnd
    ) {
        Icon(
            modifier = Modifier.clickable {
                onFavoriteClick()
            }, tint = MaterialTheme.colorScheme.secondary, imageVector = if (isFavorite) {
                Icons.Default.Favorite
            } else {
                Icons.Default.FavoriteBorder
            }, contentDescription = "Favorite Icon"
        )
    }
}


@Composable
fun MovieDetails(modifier: Modifier, movie: Movie) {
        Column(modifier = modifier.padding(horizontal = 20.dp, vertical = 3.dp)) {
            Text(text = "Director: ${movie.director}")
            Text(text = "original Title: ${movie.orTitle}")
            Text(text = "german Title: ${movie.dtTitle}")
            Text(text = "Length: ${movie.lengthMin} min")
            Text(text = "Year: ${movie.year}")
            Text(text = "Country: ${movie.country}")
            Text(text = "Production: ${movie.production}")
            Text(text = "Director: ${movie.director}")
            Text(text = "Book: ${movie.book}")
            Text(text = "Camera: ${movie.camera}")
            Text(text = "Music: ${movie.music}")
            Text(text = "Actor/Actress: ${movie.actor1}")
            Text(text = "Actor/Actress: ${movie.actor2}")
            Text(text = "Actor/Actress: ${movie.actor3}")
        }
}
