package com.example.movieappmad24.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieappmad24.widgets.SimpleBottomAppBar
import com.example.movieappmad24.widgets.SimpleTopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.extractor.text.webvtt.WebvttCssStyle.FontSizeUnit
import com.example.movieappmad24.di.InjectorUtils
import com.example.movieappmad24.models.DetailScreenViewModel
import com.example.movieappmad24.widgets.MovieDetails


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    movieId: Int,
    navController: NavController
) {
    val context = LocalContext.current

    val detailScreenViewModel: DetailScreenViewModel = viewModel(
        factory = InjectorUtils.provideDetailScreenViewModelFactory(context, movieId)
    )

    val movie by detailScreenViewModel.movies.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back Icon")
            }
            //SimpleTopAppBar( title = movie?.orTitle ?: "Movie Details")
            Box(
                modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = movie?.orTitle ?: "Movie Details", textAlign = TextAlign.Center, fontSize = 20.sp)
            }
        },
        bottomBar = {
            SimpleBottomAppBar(navController = navController)
        }
    ) { innerPadding ->
        movie?.let { movie ->
            MovieDetails(modifier = Modifier.padding(innerPadding), movie = movie)
        }
    }
}





