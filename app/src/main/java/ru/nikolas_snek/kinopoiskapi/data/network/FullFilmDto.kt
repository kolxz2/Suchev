package ru.nikolas_snek.kinopoiskapi.data.network

import ru.nikolas_snek.kinopoiskapi.doimain.models.Countries
import ru.nikolas_snek.kinopoiskapi.doimain.models.FullFilm
import ru.nikolas_snek.kinopoiskapi.doimain.models.Genres


data class FullFilmDto(
    val filmId: Int,
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

fun CountriesDto.toCountries() = Countries(
    country = country
)

fun GenresDto.toGenres() = Genres(
    genre = genre


)

fun FullFilmDto.toFullFilm() = FullFilm(
    filmId = filmId,
    nameRu = nameRu,
    posterUrl = posterUrl,
    description = description,
    countries = countries.map { it.toCountries() },
    genres = genres.map { it.toGenres() },
)