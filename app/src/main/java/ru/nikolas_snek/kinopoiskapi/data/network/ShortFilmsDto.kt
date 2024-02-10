package ru.nikolas_snek.kinopoiskapi.data.network

import ru.nikolas_snek.kinopoiskapi.doimain.models.ShortFilms

data class ShortFilmsDto(
    val filmId: Int,
    val nameRu: String,
    val year: String,
    val posterUrlPreview: String,
    val genres: List<GenresDto>,
)

data class ShortResponse(
    val pagesCount: Int,
    val films: List<ShortFilmsDto>,
)

fun ShortFilmsDto.toShortFilms() = ShortFilms(
    filmId=filmId,
    nameRu=nameRu,
    year=year,
    posterUrlPreview=posterUrlPreview,
    genres=genres.map { it.toGenres() },
)