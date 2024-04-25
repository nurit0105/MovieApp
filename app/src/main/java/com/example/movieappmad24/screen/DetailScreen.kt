package com.example.movieappmad24.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieappmad24.widgets.SimpleBottomAppBar
import com.example.movieappmad24.widgets.SimpleTopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movieappmad24.di.InjectorUtils
import com.example.movieappmad24.models.DetailScreenViewModel
import com.example.movieappmad24.widgets.CoilImage
import com.example.movieappmad24.widgets.SingleVisibleObjectGroup
import com.example.movieappmad24.widgets.Trailer

@Composable
fun DetailScreen(
    movieId: Int,
    navController: NavController
) {
    val context = LocalContext.current

    val detailScreenViewModel: DetailScreenViewModel = viewModel(
        factory = InjectorUtils.provideDetailScreenViewModelFactory(context, movieId)
    )

    val movieWithImages by detailScreenViewModel.movies.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back Icon")
            }
            SimpleTopAppBar(title = movieWithImages?.movie?.title ?: "Movie Details")
        },
        bottomBar = {
            SimpleBottomAppBar(navController = navController)
        }
    ) { innerPadding ->
        movieWithImages?.let { movie ->
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                item {
                    SingleVisibleObjectGroup(
                        movieWithImages = movie,
                        onFavoriteClick = { detailScreenViewModel.toggleFavorite() }
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Trailer(movieWithImages = movie)
                    Spacer(modifier = Modifier.height(5.dp))
                    CoilImage(movieWithImages = movie)
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
    }
}





