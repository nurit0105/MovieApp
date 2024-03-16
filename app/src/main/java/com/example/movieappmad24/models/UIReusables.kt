package com.example.movieappmad24.models


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tops(title: String) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 125.dp),
        title = { Text(text = title) })
}

@Composable
fun Bottoms( //#TODO Add Stateholder to set selected property inside the NavigationBarItems
    navController: NavController
) {
    BottomAppBar(
        actions = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.padding(start = 20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        onClick = { navController.navigate("home") }
                    ) {
                        Icon(Icons.Default.Home, contentDescription = "Home")
                    }
                    Text(text = "Home")
                }
                Column(
                    modifier = Modifier.padding(end = 20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(onClick = { navController.navigate("watchlist") }) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = "Watchlist"
                        )
                    }
                    Text(text = "Watchlist")
                }
            }
        }
    )
}

@Composable
fun <T> CoilImage(getImages: (T) -> List<String>, item: T) { //#TODO srollable Card composables ?
    val images = getImages(item)

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(images.size) { index ->
            Image(
                painter = rememberAsyncImagePainter(images[index]),
                contentDescription = "Image $index",
                modifier = Modifier
                    .size(350.dp)
            )
        }
    }
}



