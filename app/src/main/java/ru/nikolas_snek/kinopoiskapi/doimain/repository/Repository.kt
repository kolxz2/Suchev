package ru.nikolas_snek.kinopoiskapi.doimain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.nikolas_snek.kinopoiskapi.data.database.FilmEntity
import ru.nikolas_snek.kinopoiskapi.doimain.models.FullFilm
import ru.nikolas_snek.kinopoiskapi.doimain.models.ShortFilms

interface Repository {

  suspend fun getAllFilms() : Flow<PagingData<ShortFilms>>

  suspend fun getFilmDetailInfo(filmId: Int) : RequestResult<FullFilm>

  suspend fun saveToFavorites(filmId: Int)

  suspend fun getFilmFromFavorites(): List<FilmEntity>
}