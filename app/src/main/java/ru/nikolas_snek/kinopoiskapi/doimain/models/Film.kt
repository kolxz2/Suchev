package ru.nikolas_snek.kinopoiskapi.doimain.models

data class Film (
    val filmId: Int,
    val nameRu: String,
    val year: String,
    val posterUrlPreview: String,
)