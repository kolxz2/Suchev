package ru.nikolas_snek.kinopoiskapi.data

import ru.nikolas_snek.kinopoiskapi.data.network.BaseRepository
import ru.nikolas_snek.kinopoiskapi.data.network.ResultRequest
import ru.nikolas_snek.kinopoiskapi.data.network.RetrofitInstance
import ru.nikolas_snek.kinopoiskapi.data.network.ShortFilmDto
import ru.nikolas_snek.kinopoiskapi.doimain.Repository
import ru.nikolas_snek.kinopoiskapi.doimain.models.FullFilm
import ru.nikolas_snek.kinopoiskapi.doimain.models.ShortFilms

class RepositoryImpl : Repository ,  BaseRepository(){
    override suspend fun getAllFilms() : ResultRequest<List<ShortFilmDto>>  {
        return safeApiCall{
            val response = RetrofitInstance.api.getShortFilms(type = "TOP_100_POPULAR_FILMS", page = 1) as List<ShortFilmDto>
        }
    }

    override suspend fun getFilmDetailInfo(): List<FullFilm> {
        TODO("Not yet implemented")
    }

}