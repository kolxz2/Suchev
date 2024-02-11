package ru.nikolas_snek.kinopoiskapi.presentation.film_info

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FilmInfoFragmentViewModelFactory(
    private val filmId: Int,
    private val application: Application
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FilmInfoViewModel::class.java)){
            return FilmInfoViewModel(filmId, application) as T
        }
        throw RuntimeException("Unknown View Modal class $modelClass ")

    }
}