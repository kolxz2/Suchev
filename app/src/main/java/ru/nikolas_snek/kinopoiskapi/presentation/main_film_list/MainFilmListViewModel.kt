package ru.nikolas_snek.kinopoiskapi.presentation.main_film_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.nikolas_snek.kinopoiskapi.data.RepositoryImpl
import ru.nikolas_snek.kinopoiskapi.doimain.GetAllFilmsUseCase
import ru.nikolas_snek.kinopoiskapi.doimain.models.ShortFilms
import ru.nikolas_snek.kinopoiskapi.doimain.repository.RequestResult

class MainFilmListViewModel : ViewModel() {
    private val repositoryImpl = RepositoryImpl()

    private val _filmList = MutableLiveData<List<ShortFilms>?>()
    val filmsList: LiveData<List<ShortFilms>?>
        get() = _filmList

    private val _loadingProgress = MutableLiveData<Boolean>()
    val loadingProgress: LiveData<Boolean>
        get() = _loadingProgress

    fun getAllFilms() {
        viewModelScope.launch {
            _loadingProgress.value = true
            val requestResult = GetAllFilmsUseCase(repositoryImpl).invoke()
            _loadingProgress.value = false
            when (requestResult) {
                is RequestResult.Success -> {
                    _filmList.value = requestResult.data
                }
                is RequestResult.Error -> {
                    _filmList.value = null
                }
            }


        }
    }
}