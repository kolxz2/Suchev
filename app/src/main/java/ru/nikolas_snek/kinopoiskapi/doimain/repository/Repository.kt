package ru.nikolas_snek.kinopoiskapi.doimain.repository

import ru.nikolas_snek.kinopoiskapi.doimain.models.FullFilm
import ru.nikolas_snek.kinopoiskapi.doimain.models.ShortFilms

interface Repository {

  suspend fun getAllFilms() : RequestResult<List<ShortFilms>>

  suspend fun getFilmDetailInfo(filmId: Int) : RequestResult<FullFilm>
}