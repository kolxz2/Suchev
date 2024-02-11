package ru.nikolas_snek.kinopoiskapi.presentation.film_info

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.nikolas_snek.kinopoiskapi.data.RepositoryImpl
import ru.nikolas_snek.kinopoiskapi.data.database.FilmDatabase
import ru.nikolas_snek.kinopoiskapi.doimain.GetFilmDetailInfoUseCase
import ru.nikolas_snek.kinopoiskapi.doimain.models.FullFilm
import ru.nikolas_snek.kinopoiskapi.doimain.repository.RequestResult

class FilmInfoViewModel(
    private val filmId: Int,
    application: Application
)  : AndroidViewModel(application) {

    private val repositoryImpl: RepositoryImpl

    init {
        val userDao = FilmDatabase.getDatabase(application).userDao()
        repositoryImpl = RepositoryImpl(userDao)
    }

    private val _filmInfo = MutableLiveData<FullFilm?>()
    val filmInfo: LiveData<FullFilm?>
        get() = _filmInfo

    private val _loadingProgress = MutableLiveData<Boolean>()
    val loadingProgress: LiveData<Boolean>
        get() = _loadingProgress

    fun loadFilmInfo() {
        viewModelScope.launch {
            _loadingProgress.value = true
            val requestResult =
                GetFilmDetailInfoUseCase(repository = repositoryImpl, filmId = filmId).invoke()
            _loadingProgress.value = false
            when(requestResult){
                is RequestResult.Success -> {
                    _filmInfo.value = requestResult.data
                }

                is RequestResult.Error -> {
                    _filmInfo.value = null
                }

            }

        }
    }


}