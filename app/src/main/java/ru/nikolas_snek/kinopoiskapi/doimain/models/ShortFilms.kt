package ru.nikolas_snek.kinopoiskapi.doimain.models

data class ShortFilms(
    val filmId: Int,
    val nameRu: String,
    val year: String?,
    val posterUrlPreview: String,
    val genres: List<Genres>
)
