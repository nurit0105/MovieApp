package com.example.movieappmad24.models

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun <T> ListOfVisibleObjectGroups(
    modifier: Modifier,
    items: List<T>,
    itemContent: @Composable (item: T) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items(items) { item ->
            itemContent(item)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SingleVisibleObjectGroup(
    item: T,
    id: (T) -> String,
    title: (T) -> String,
    labels: List<(T) -> String>,
    contentDescription: List<(T) -> String>,
    getImages: (T) -> List<String>,
    onClick: (String) -> Unit
) {
    var expandedState by remember {
        mutableStateOf(false)
    }

    Card(
        onClick = { onClick(id(item)) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = ShapeDefaults.Large,
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = getImages(item).first(),
                    contentDescription = "Image of Item",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Icon(
                        tint = MaterialTheme.colorScheme.secondary,
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Add to favorites"
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = title(item))
                Icon(
                    modifier = Modifier
                        .clickable {
                            expandedState = !expandedState
                        },
                    imageVector =
                    if (expandedState) Icons.Filled.KeyboardArrowDown
                    else Icons.Default.KeyboardArrowUp, contentDescription = "show more"
                )
            }
            if (expandedState) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize(
                            animationSpec = tween(
                                delayMillis = 300,
                                easing = LinearOutSlowInEasing
                            )
                        ),
                    onClick = {
                        expandedState = !expandedState
                    }
                ) {
                    Row {
                        Column {
                            for (i in 0 until minOf(labels.size, contentDescription.size)) {
                                Text(
                                    modifier = Modifier.padding(start = 10.dp),
                                    text = "${labels[i](item)}: ${contentDescription[i](item)}"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SingleMovieObjectGroup(
    modifier: Modifier = Modifier,
    item: Movie,
    onClick: (String) -> Unit
) {
    SingleVisibleObjectGroup(
        item = item,
        id = { it.id },
        title = { it.title },
        labels = listOf(
            { movie -> "Director" },
            { movie -> "Released" },
            { movie -> "Genre" },
            { movie -> "Actors" },
            { movie -> "Rating" },
            { movie -> "Plot" }
        ),
        contentDescription = listOf(
            { it.director },
            { it.year },
            { it.genre },
            { it.actors },
            { it.rating },
            { it.plot }
        ),
        getImages = { it.images },
        onClick = { movieId -> onClick(movieId) }
    )
}