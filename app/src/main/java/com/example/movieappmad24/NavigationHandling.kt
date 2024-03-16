package com.example.movieappmad24

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieappmad24.Screens.DetailScreen
import com.example.movieappmad24.Screens.HomeScreen
import com.example.movieappmad24.Screens.WatchListScreen
import com.example.movieappmad24.models.getMovies

sealed class NavigationHandling(val route: String) {
    object Home : NavigationHandling("home")
    object Detail : NavigationHandling("detail/{movieId}")

    object Watchlist : NavigationHandling("watchlist")
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationHandling.Home.route) {
        composable(NavigationHandling.Home.route) {
            HomeScreen(navController)
        }
        composable(NavigationHandling.Detail.route) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")
            if (movieId != null) {
                val movie = getMovies().find { it.id == movieId }
                if (movie != null) {
                    DetailScreen(movieId, navController)
                }
            }
        }

        composable(NavigationHandling.Watchlist.route) {
            WatchListScreen(navController)
        }
    }
}