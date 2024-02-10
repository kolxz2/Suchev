package ru.nikolas_snek.kinopoiskapi.presentation.film_info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.nikolas_snek.kinopoiskapi.data.RepositoryImpl
import ru.nikolas_snek.kinopoiskapi.doimain.GetFilmDetailInfo
import ru.nikolas_snek.kinopoiskapi.doimain.models.FullFilm
import ru.nikolas_snek.kinopoiskapi.doimain.models.ShortFilms

class FilmInfoViewModel(
    private val filmId: Int,
) : ViewModel() {

    private val repositoryImpl = RepositoryImpl()

    private val _filmInfo = MutableLiveData<FullFilm>()
    val filmInfo: LiveData<FullFilm>
        get() = _filmInfo

    fun loadFilmInfo() {
        viewModelScope.launch {
            _filmInfo.value = GetFilmDetailInfo(repository = repositoryImpl).invoke(filmId = filmId)
        }
    }
}