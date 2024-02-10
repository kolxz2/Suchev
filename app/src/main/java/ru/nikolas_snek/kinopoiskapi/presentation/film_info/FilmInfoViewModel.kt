package ru.nikolas_snek.kinopoiskapi.presentation.film_info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.nikolas_snek.kinopoiskapi.data.RepositoryImpl
import ru.nikolas_snek.kinopoiskapi.doimain.GetFilmDetailInfo
import ru.nikolas_snek.kinopoiskapi.doimain.models.FullFilm
import ru.nikolas_snek.kinopoiskapi.doimain.repository.RequestResult

class FilmInfoViewModel(
    private val filmId: Int,
) : ViewModel() {

    private val repositoryImpl = RepositoryImpl()

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
                GetFilmDetailInfo(repository = repositoryImpl, filmId = filmId).invoke()
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