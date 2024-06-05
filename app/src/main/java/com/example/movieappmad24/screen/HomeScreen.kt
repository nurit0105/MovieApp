package com.example.movieappmad24.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movieappmad24.di.InjectorUtils
import com.example.movieappmad24.models.HomeScreenViewModel
import com.example.movieappmad24.widgets.ListOfVisibleObjectGroups
import com.example.movieappmad24.widgets.SimpleBottomAppBar
import com.example.movieappmad24.widgets.SimpleTopAppBar


 @Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    val homeScreenViewModel: HomeScreenViewModel = viewModel(factory = InjectorUtils.provideHomeScreenViewModelFactory(context))
    val movies by homeScreenViewModel.movies.collectAsState()


     Scaffold(
        topBar = {
            SimpleTopAppBar(title = "Movie Status")
        },
        bottomBar = {
            SimpleBottomAppBar(navController = navController)
        }
    ) { innerPadding ->
        ListOfVisibleObjectGroups(
            modifier = Modifier.padding(innerPadding),
            movies = movies,
            navController = navController,
            viewModel = homeScreenViewModel
        )
    }
}