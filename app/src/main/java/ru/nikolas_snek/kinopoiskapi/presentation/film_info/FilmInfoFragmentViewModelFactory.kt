package ru.nikolas_snek.kinopoiskapi.presentation.film_info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FilmInfoFragmentViewModelFactory(
    private val filmId: Int,
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FilmInfoViewModel::class.java)){
            return FilmInfoViewModel(filmId) as T
        }
        throw RuntimeException("Unknown View Modal class $modelClass ")

    }
}