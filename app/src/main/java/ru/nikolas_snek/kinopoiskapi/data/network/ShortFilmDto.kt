package ru.nikolas_snek.kinopoiskapi.data.network

data class ShortFilmDto (
    val id: Int,
    val nameRu: String,
    val year: String,
    val posterUrlPreview: String,
)