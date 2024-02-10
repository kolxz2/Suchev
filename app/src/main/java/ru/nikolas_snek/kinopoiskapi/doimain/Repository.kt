package ru.nikolas_snek.kinopoiskapi.doimain

import ru.nikolas_snek.kinopoiskapi.doimain.models.FullFilm
import ru.nikolas_snek.kinopoiskapi.doimain.models.ShortFilms

interface Repository {

  suspend fun  getAllFilms() : List<ShortFilms>

  suspend fun getFilmDetailInfo(filmId: Int) : FullFilm
}