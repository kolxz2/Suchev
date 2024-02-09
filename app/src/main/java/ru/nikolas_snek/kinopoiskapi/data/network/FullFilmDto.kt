package ru.nikolas_snek.kinopoiskapi.data.network



data class FullFilmsDto(
    val id: Int,
    val nameRu: String,
    val posterUrl: String,
    val description: String,
    val countries: List<CountriesDto>,
    val genres: List<GenresDto>,
)
data class CountriesDto(
    val country: String,
)

data class GenresDto(
    val genre: String,
)