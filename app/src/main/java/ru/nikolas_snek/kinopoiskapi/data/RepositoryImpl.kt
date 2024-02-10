package ru.nikolas_snek.kinopoiskapi.data

import ru.nikolas_snek.kinopoiskapi.data.network.KinopoiskAPI
import ru.nikolas_snek.kinopoiskapi.data.network.RetrofitInstance
import ru.nikolas_snek.kinopoiskapi.data.network.toFullFilm
import ru.nikolas_snek.kinopoiskapi.data.network.toShortFilms
import ru.nikolas_snek.kinopoiskapi.doimain.Repository
import ru.nikolas_snek.kinopoiskapi.doimain.models.FullFilm
import ru.nikolas_snek.kinopoiskapi.doimain.models.ShortFilms

class RepositoryImpl : Repository {
    companion object {
        private const val X_API_KEY = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"
    }

    private val movieService = RetrofitInstance.retrofit.create(KinopoiskAPI::class.java)
    override suspend fun getAllFilms(): List<ShortFilms> =
        movieService.getPopularFilms(apiKey = X_API_KEY, page = 1).films.map {
            it.toShortFilms()
        }

    override suspend fun getFilmDetailInfo(filmId: Int): FullFilm {
        return movieService.getFullFilm(filmId = filmId, apiKey = X_API_KEY).toFullFilm()
    }

}