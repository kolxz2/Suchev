package ru.nikolas_snek.kinopoiskapi.presentation.main_film_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.nikolas_snek.kinopoiskapi.data.RepositoryImpl
import ru.nikolas_snek.kinopoiskapi.doimain.GetAllFilmsUseCase
import ru.nikolas_snek.kinopoiskapi.doimain.models.ShortFilms

class MainFilmListViewModel : ViewModel() {
    private val repositoryImpl = RepositoryImpl()

    private val _filmsList = MutableLiveData<List<ShortFilms>>()
    val filmsList :LiveData<List<ShortFilms>>
        get() = _filmsList

    fun getAllFilms(){
        viewModelScope.launch {
            _filmsList.value = GetAllFilmsUseCase(repositoryImpl).invoke()


        }
    }
}