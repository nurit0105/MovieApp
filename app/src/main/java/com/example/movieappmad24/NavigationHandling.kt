package com.example.movieappmad24

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieappmad24.screen.AddMovie
import com.example.movieappmad24.screen.DetailScreen
import com.example.movieappmad24.screen.HomeScreen
import com.example.movieappmad24.screen.WatchlistScreen


sealed class NavigationHandling(val route: String) {
    object Home : NavigationHandling("home")
    object Detail : NavigationHandling("detail/{movieId}")
    object Watchlist : NavigationHandling("favorites")
    object AddMovie: NavigationHandling("addMovie")
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationHandling.Home.route) {
        composable(NavigationHandling.Home.route) {
            HomeScreen(navController)
        }
        composable(NavigationHandling.Detail.route) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull()
            if (movieId != null) {
                DetailScreen(movieId, navController)
            }
        }
        composable(NavigationHandling.Watchlist.route) {
            WatchlistScreen(navController)
        }
        composable(NavigationHandling.AddMovie.route) {
            AddMovie(navController)
        }
    }
}