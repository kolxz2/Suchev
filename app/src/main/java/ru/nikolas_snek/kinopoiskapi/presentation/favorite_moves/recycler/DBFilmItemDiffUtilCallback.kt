package ru.nikolas_snek.kinopoiskapi.presentation.favorite_moves.recycler

import androidx.recyclerview.widget.DiffUtil
import ru.nikolas_snek.kinopoiskapi.data.database.FilmEntity

class DBFilmItemDiffUtilCallback: DiffUtil.ItemCallback<FilmEntity>() {

    override fun areItemsTheSame(oldItem: FilmEntity, newItem: FilmEntity): Boolean {
        return oldItem.filmId == newItem.filmId
    }

    override fun areContentsTheSame(oldItem: FilmEntity, newItem: FilmEntity): Boolean {
        return oldItem == newItem
    }
}