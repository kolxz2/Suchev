package ru.nikolas_snek.kinopoiskapi.data.network

import ru.nikolas_snek.kinopoiskapi.doimain.models.ShortFilms

fun ShortFilmDto.toShortFilm(): ShortFilms = ShortFilms(
    id = id,
    nameRu = nameRu,
    year = year,
    posterUrlPreview = posterUrlPreview,
)