package com.example.movieappmad24.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movieappmad24.di.InjectorUtils
import com.example.movieappmad24.models.AddMovieViewModel
import com.example.movieappmad24.widgets.NewMovie
import com.example.movieappmad24.widgets.SimpleBottomAppBar
import com.example.movieappmad24.widgets.SimpleTopAppBar

@Composable
fun AddMovie(navController: NavController) {
    val context = LocalContext.current
    val addMovieViewModel: AddMovieViewModel = viewModel(factory = InjectorUtils.provideAddMovieViewModelFactory(context))
    val movies by addMovieViewModel.movies.collectAsState()


    Scaffold(
        topBar = {
            SimpleTopAppBar(title = "Movie Status")
        },
        bottomBar = {
            SimpleBottomAppBar(navController = navController)
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
           NewMovie(navController = navController,  viewModel = addMovieViewModel)
        }
    }
}