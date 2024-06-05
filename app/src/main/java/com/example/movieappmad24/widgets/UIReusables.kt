package com.example.movieappmad24.widgets


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopAppBar(title: String) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 125.dp),
        title = { Text(text = title) })
}

data class BottomNavigationItem(
    val title: String,
    val icon: ImageVector
)

@Composable
fun SimpleBottomAppBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val items = listOf(
        BottomNavigationItem(
            title = "watchlist",
            icon = Icons.Filled.Favorite
        ),
        BottomNavigationItem(
            title = "home",
            icon = Icons.Filled.Home
        ),
        BottomNavigationItem(
            title = "addMovie",
            icon = Icons.Filled.AddCircle
        )
    )

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = currentRoute == item.title,
                onClick = {
                    navController.navigate(item.title) {
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Box(
                        modifier = Modifier
                            .clickable {
                                navController.navigate(item.title) {
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                            .padding(8.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title,
                                tint = if (currentRoute == item.title) Color.White else Color.Black
                            )
                            Text(
                                text = item.title,
                                color = if (currentRoute == item.title) Color.White else Color.Black,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                    }
                }
            )
        }
    }
}
