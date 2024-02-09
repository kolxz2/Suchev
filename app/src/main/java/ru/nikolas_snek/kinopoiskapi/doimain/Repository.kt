package ru.nikolas_snek.kinopoiskapi.doimain

import ru.nikolas_snek.kinopoiskapi.data.network.ResultRequest
import ru.nikolas_snek.kinopoiskapi.data.network.ShortFilmDto
import ru.nikolas_snek.kinopoiskapi.doimain.models.FullFilm
import ru.nikolas_snek.kinopoiskapi.doimain.models.ShortFilms

interface Repository {

  suspend fun  getAllFilms() : ResultRequest<List<ShortFilmDto>>

  suspend fun getFilmDetailInfo() : List<FullFilm>
}