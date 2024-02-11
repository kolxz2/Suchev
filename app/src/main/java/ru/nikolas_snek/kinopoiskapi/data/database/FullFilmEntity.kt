package ru.nikolas_snek.kinopoiskapi.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FilmEntity (
    @PrimaryKey
    val filmId: Int,
    val nameRu: String,
    val year: String,
    val posterUrlPreview: String,
)

