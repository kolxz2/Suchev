package ru.nikolas_snek.kinopoiskapi.presentation.main_film_list.recycler

import androidx.recyclerview.widget.DiffUtil
import ru.nikolas_snek.kinopoiskapi.doimain.models.ShortFilms

class FilmItemDiffUtilCallback: DiffUtil.ItemCallback<ShortFilms>() {

    override fun areItemsTheSame(oldItem: ShortFilms, newItem: ShortFilms): Boolean {
        return oldItem.filmId == newItem.filmId
    }

    override fun areContentsTheSame(oldItem: ShortFilms, newItem: ShortFilms): Boolean {
        return oldItem == newItem
    }
}