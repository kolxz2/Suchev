package ru.nikolas_snek.kinopoiskapi.presentation.main_film_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ru.nikolas_snek.kinopoiskapi.data.RepositoryImpl
import ru.nikolas_snek.kinopoiskapi.data.database.FilmDatabase
import ru.nikolas_snek.kinopoiskapi.doimain.GetAllFilmsUseCase
import ru.nikolas_snek.kinopoiskapi.doimain.SaveToFavoritesUseCase
import ru.nikolas_snek.kinopoiskapi.doimain.models.ShortFilms

class MainFilmListViewModel(application: Application) : AndroidViewModel(application) {
    private val repositoryImpl: RepositoryImpl

    init {
        val userDao = FilmDatabase.getDatabase(application).userDao()
        repositoryImpl = RepositoryImpl(userDao)
    }

    lateinit var filmsFlow: Flow<PagingData<ShortFilms>>


    fun getAllFilms() {
        viewModelScope.launch {
            filmsFlow = GetAllFilmsUseCase(repositoryImpl).invoke()
        }
    }

    fun saveFilmToFavorite(filmId: Int) {
        viewModelScope.launch {
            SaveToFavoritesUseCase(repositoryImpl, filmId).invoke()
        }

    }
}