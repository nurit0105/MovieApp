package com.example.movieappmad24.widgets


import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.movieappmad24.data.MovieWithImages
import com.example.movieappmad24.models.MoviesViewModel


@Composable
fun ListOfVisibleObjectGroups(
    modifier: Modifier = Modifier,
    movies: List<MovieWithImages>,
    navController: NavController,
    viewModel: MoviesViewModel
) {
    Log.d("ListOfVisibleObjectGroups", "Displaying ${movies.size} movies")

    LazyColumn(modifier = modifier) {
        try {
            items(movies) { movieWithImages ->
                SingleVisibleObjectGroup(
                    movieWithImages = movieWithImages,
                    onFavoriteClick = {
                        viewModel.toggleFavoriteMovie(movieWithImages)
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

@Composable
fun SingleVisibleObjectGroup(
    modifier: Modifier = Modifier,
    movieWithImages: MovieWithImages,
    onFavoriteClick: (MovieWithImages) -> Unit = {},
    onItemClick: (Int) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                onItemClick(movieWithImages.movie.id)
            },
        shape = ShapeDefaults.Large,
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column {

            // Movie header
            MovieCardHeader(
                imageUrl = if (movieWithImages.images.isNotEmpty()) movieWithImages.images[0].url else "",
                isFavorite = movieWithImages.movie.isFavorite,
                onFavoriteClick = {
                    onFavoriteClick(movieWithImages)
                }
            )
            MovieDetails(modifier = modifier.padding(12.dp), movieWithImages = movieWithImages)
        }
    }
}

@Composable
fun MovieCardHeader(
    imageUrl: String,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        MovieImage(imageUrl)

        FavoriteIcon(
            isFavorite = isFavorite,
            onFavoriteClick = onFavoriteClick
        )
    }
}

@Composable
fun MovieImage(imageUrl: String) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentScale = ContentScale.Crop,
        contentDescription = "movie poster",
        loading = {
            CircularProgressIndicator()
        },
        error = {
            Text(text = "Failed to load image")
            Log.e("MovieImage", "Failed to load image URL: $imageUrl")
        }
    )
}

@Composable
fun FavoriteIcon(
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        Icon(
            modifier = Modifier.clickable {
                onFavoriteClick()
            },
            tint = MaterialTheme.colorScheme.secondary,
            imageVector =
            if (isFavorite) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = "Favorite Icon"
        )
    }
}

@Composable
fun MovieDetails(modifier: Modifier, movieWithImages: MovieWithImages) {
    var showDetails by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = movieWithImages.movie.title)
        Icon(
            modifier = Modifier
                .clickable {
                    showDetails = !showDetails
                },
            imageVector =
            if (showDetails) Icons.Filled.KeyboardArrowDown
            else Icons.Default.KeyboardArrowUp,
            contentDescription = "show more"
        )
    }

    AnimatedVisibility(
        visible = showDetails,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column(modifier = modifier) {
            Text(text = "Director: ${movieWithImages.movie.director}")
            Text(text = "Released: ${movieWithImages.movie.year}")
            Text(text = "Genre: ${movieWithImages.movie.genre}")
            Text(text = "Actors: ${movieWithImages.movie.actors}")
            Text(text = "Rating: ${movieWithImages.movie.rating}")

            Divider(modifier = Modifier.padding(3.dp))
        }
    }
}

@Composable
fun Trailer(movieWithImages: MovieWithImages) {
    var lifecycle by remember {
        mutableStateOf(Lifecycle.Event.ON_CREATE)
    }

    val context = LocalContext.current

    val uri = getResourceUri(context, movieWithImages.movie.trailer)

    val mediaItem = MediaItem.fromUri(uri)

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
            addListener(object : Player.Listener {
                override fun onPlaybackStateChanged(state: Int) {
                    Log.d("MovieTrailer", "Playback state changed: $state")
                }
            })
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycle = event
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            exoPlayer.release()
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f),
        factory = {
            PlayerView(context).also { playerView ->
                playerView.player = exoPlayer
            }
        },
        update = {
            when (lifecycle) {
                Lifecycle.Event.ON_RESUME -> {
                    it.onPause()
                    it.player?.pause()
                }

                Lifecycle.Event.ON_PAUSE -> {
                    it.onResume()
                }

                else -> Unit
            }
        }
    )
}

fun getResourceUri(context: Context, resourceId: Int): Uri {
    return Uri.parse("android.resource://${context.packageName}/$resourceId")
}
