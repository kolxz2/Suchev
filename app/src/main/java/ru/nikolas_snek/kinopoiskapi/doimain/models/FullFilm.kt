package ru.nikolas_snek.kinopoiskapi.doimain.models

data class FullFilm(
    val id: Int,
    val nameRu: String,
    val posterUrl: String,
    val description: String,
    val countries: List<Countries>,
    val genres: List<Genres>,
)

data class Countries(
    val country: String,
)

data class Genres(
    val genre: String,
)