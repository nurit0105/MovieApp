package com.example.movieappmad24

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

@Composable
fun WatchList() {
    UserInterface(
        "Watch List",
        "Home",
        iconLeft = { Icon(Icons.Default.Home, contentDescription = "Home") },
        "Watchlist",
        iconRight = { Icon(Icons.Default.Star, contentDescription = "Watchlist") })
    }
