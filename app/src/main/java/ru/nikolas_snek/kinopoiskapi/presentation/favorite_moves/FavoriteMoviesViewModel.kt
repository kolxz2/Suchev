package ru.nikolas_snek.kinopoiskapi.presentation.favorite_moves

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.nikolas_snek.kinopoiskapi.data.RepositoryImpl
import ru.nikolas_snek.kinopoiskapi.data.database.FilmDatabase
import ru.nikolas_snek.kinopoiskapi.data.database.FilmEntity
import ru.nikolas_snek.kinopoiskapi.doimain.GetFilmFromFavorites

class FavoriteMoviesViewModel(application: Application) : AndroidViewModel(application) {

    private val repositoryImpl: RepositoryImpl

    init {
        val userDao = FilmDatabase.getDatabase(application).userDao()
        repositoryImpl = RepositoryImpl(userDao)
    }


    private var _filmsFromBD = MutableLiveData<List<FilmEntity>>()
    val filmsFromBD : LiveData<List<FilmEntity>>
        get() = _filmsFromBD


    private var _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean>
        get() = _loading

    fun loadAllSavedFilms(){
        viewModelScope.launch {
            _loading.value = true
            _filmsFromBD.value = GetFilmFromFavorites(repositoryImpl).invoke()
            _loading.value = false
        }

    }

}