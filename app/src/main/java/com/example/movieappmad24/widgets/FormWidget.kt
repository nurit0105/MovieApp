package com.example.movieappmad24.widgets

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.movieappmad24.data.Movie
import com.example.movieappmad24.models.AddMovieViewModel
import com.example.movieappmad24.models.DetailScreenViewModel
import com.example.movieappmad24.models.HomeScreenViewModel
import com.example.movieappmad24.models.WatchlistScreenViewModel

@Composable
fun NewMovie(
    navController: NavController,
    viewModel: AddMovieViewModel
) {
    val focusManager = LocalFocusManager.current
    var filledOrTitel by remember { mutableStateOf("") }
    var filledDtTitle by remember { mutableStateOf("") }
    var filledLengthMin by remember { mutableStateOf("") }
    var filledYear by remember { mutableStateOf("") }
    var filledCountry by remember { mutableStateOf("") }
    var filledProduction by remember { mutableStateOf("") }
    var filledDirector by remember { mutableStateOf("") }
    var filledBook by remember { mutableStateOf("") }
    var filledCamera by remember { mutableStateOf("") }
    var filledMusic by remember { mutableStateOf("") }
    var filledActor1 by remember { mutableStateOf("") }
    var filledActor2 by remember { mutableStateOf("") }
    var filledActor3 by remember { mutableStateOf("") }

    var showError by remember { mutableStateOf(false) }

    val validateFields: () -> Boolean = {
        filledOrTitel.isNotBlank() &&
                filledDtTitle.isNotBlank() &&
                filledLengthMin.isNotBlank() &&
                filledYear.isNotBlank() &&
                filledCountry.isNotBlank() &&
                filledProduction.isNotBlank() &&
                filledDirector.isNotBlank()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(6.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = filledOrTitel,
                    onValueChange = { filledOrTitel = it },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
                    label = { Text(text = "Enter Original Title") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Star"
                        )
                    },
                    isError = showError && filledOrTitel.isBlank(),
                    supportingText = {
                        if (showError && filledOrTitel.isBlank()) {
                            Text(text = "*required", color = MaterialTheme.colorScheme.error)
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
                )
            }
            item {
                Spacer(modifier = Modifier.padding(3.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = filledDtTitle,
                    onValueChange = { filledDtTitle = it },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
                    label = { Text(text = "Enter German Title") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Star"
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
                )
            }
            item {
                Spacer(modifier = Modifier.padding(6.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = filledLengthMin,
                    onValueChange = { filledLengthMin = it },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
                    label = { Text(text = "Enter Length") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = "Notifications"
                        )
                    },
                    suffix = { Text(text = "min") },
                    isError = showError && filledLengthMin.isBlank(),
                    supportingText = {
                        if (showError && filledLengthMin.isBlank()) {
                            Text(text = "*required", color = MaterialTheme.colorScheme.error)
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
                )
            }
            item {
                Spacer(modifier = Modifier.padding(3.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = filledYear,
                    onValueChange = { filledYear = it },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
                    label = { Text(text = "Enter Year") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.DateRange,
                            contentDescription = "DateRange"
                        )
                    },
                    isError = showError && filledYear.isBlank(),
                    supportingText = {
                        if (showError && filledYear.isBlank()) {
                            Text(text = "*required", color = MaterialTheme.colorScheme.error)
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
                )
            }
            item {
                Spacer(modifier = Modifier.padding(3.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = filledCountry,
                    onValueChange = { filledCountry = it },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
                    label = { Text(text = "Enter Country") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Place,
                            contentDescription = "Place"
                        )
                    },
                    isError = showError && filledCountry.isBlank(),
                    supportingText = {
                        if (showError && filledCountry.isBlank()) {
                            Text(text = "*required", color = MaterialTheme.colorScheme.error)
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
                )
            }
            item {
                Spacer(modifier = Modifier.padding(3.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = filledProduction,
                    onValueChange = { filledProduction = it },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
                    label = { Text(text = "Enter Production") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Build,
                            contentDescription = "Build"
                        )
                    },
                    isError = showError && filledProduction.isBlank(),
                    supportingText = {
                        if (showError && filledProduction.isBlank()) {
                            Text(text = "*required", color = MaterialTheme.colorScheme.error)
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
                )
            }
            item {
                Spacer(modifier = Modifier.padding(3.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = filledDirector,
                    onValueChange = { filledDirector = it },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
                    label = { Text(text = "Enter Director") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Face,
                            contentDescription = "Face"
                        )
                    },
                    isError = showError && filledDirector.isBlank(),
                    supportingText = {
                        if (showError && filledDirector.isBlank()) {
                            Text(text = "*required", color = MaterialTheme.colorScheme.error)
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
                )
            }
            item {
                Spacer(modifier = Modifier.padding(3.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = filledBook,
                    onValueChange = { filledBook = it },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
                    label = { Text(text = "Enter Book") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Face"
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
                )
            }
            item {
                Spacer(modifier = Modifier.padding(6.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = filledCamera,
                    onValueChange = { filledCamera = it },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
                    label = { Text(text = "Enter Camera") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = "PlayArrow"
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down)})
                )
            }
            item {
                Spacer(modifier = Modifier.padding(6.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = filledMusic,
                    onValueChange = { filledMusic = it },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
                    label = { Text(text = "Enter Music") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = "PlayArrow"
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
                )
            }
            item {
                Spacer(modifier = Modifier.padding(6.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = filledActor1,
                    onValueChange = { filledActor1 = it },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
                    label = { Text(text = "Enter first Actor/Actress") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Person"
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
                )
            }
            item {
                Spacer(modifier = Modifier.padding(6.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = filledActor2,
                    onValueChange = { filledActor2 = it },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
                    label = { Text(text = "Enter second Actor/Actress") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Person"
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
                )
            }
            item {
                Spacer(modifier = Modifier.padding(6.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = filledActor3,
                    onValueChange = { filledActor3 = it },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
                    label = { Text(text = "Enter third Actor/Actress") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Person"
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(  onDone = { focusManager.clearFocus() })
                )
                Spacer(modifier = Modifier.padding(3.dp))
            }
            item {
                var submitted by remember { mutableStateOf(false) }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        showError = !validateFields()
                        if (showError) return@Button
                        val newMovie = Movie(
                            orTitle = filledOrTitel,
                            dtTitle = filledDtTitle,
                            lengthMin = filledLengthMin.toIntOrNull() ?: 0,
                            year = filledYear.toIntOrNull() ?: 0,
                            country = filledCountry,
                            production = filledProduction,
                            director = filledDirector,
                            book = filledBook,
                            camera = filledCamera,
                            music = filledMusic,
                            actor1 = filledActor1,
                            actor2 = filledActor2,
                            actor3 = filledActor3
                        )
                        viewModel.addMovie(newMovie)
                        submitted = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (showError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                ) {
                    Text(text = "Submit")
                }
                if(submitted) {
                    Text(text = "Movie Added")
                }
            }
        }
    }
}