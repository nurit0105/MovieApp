package com.example.movieappmad24

import MoviesViewModel
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieappmad24.screen.DetailScreen
import com.example.movieappmad24.screen.HomeScreen
import com.example.movieappmad24.screen.WatchlistScreen


sealed class NavigationHandling(val route: String) {
    object Home : NavigationHandling("home")
    object Detail : NavigationHandling("detail/{movieId}")
    object Watchlist : NavigationHandling("watchlist")
}

@Composable
fun AppNavigation(navController: NavHostController, moviesViewModel: MoviesViewModel) {
    NavHost(navController, startDestination = NavigationHandling.Home.route) {
        composable(NavigationHandling.Home.route) {
            HomeScreen(navController, moviesViewModel = moviesViewModel)
        }
        composable(NavigationHandling.Detail.route) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull()
            if (movieId != null) {
                DetailScreen(movieId, navController, moviesViewModel = moviesViewModel)
            }
        }
        composable(NavigationHandling.Watchlist.route) {
            WatchlistScreen(navController, moviesViewModel = moviesViewModel)
        }
    }
}