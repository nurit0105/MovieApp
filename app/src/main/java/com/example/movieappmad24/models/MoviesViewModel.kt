import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad24.data.Movie
import com.example.movieappmad24.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MoviesViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    private val _favoriteMovies = MutableStateFlow<List<Movie>>(emptyList())
    val favoriteMovies: StateFlow<List<Movie>> = _favoriteMovies.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllMovies().collect { movieList ->
                _movies.value = movieList
                _favoriteMovies.value = movieList.filter { it.isFavorite }
            }
        }
    }

    fun toggleFavoriteMovie(movie: Movie) {
        movie.isFavorite = !movie.isFavorite

        viewModelScope.launch {
            repository.update(movie)
            val updatedMovies = movies.value.map { m ->
                if (m.id == movie.id) movie else m
            }
            _movies.value = updatedMovies
            _favoriteMovies.value = updatedMovies.filter { it.isFavorite }
        }
    }
}
