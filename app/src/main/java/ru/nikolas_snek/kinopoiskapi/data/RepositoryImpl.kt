package ru.nikolas_snek.kinopoiskapi.data

import ru.nikolas_snek.kinopoiskapi.data.network.KinopoiskAPI
import ru.nikolas_snek.kinopoiskapi.data.network.RetrofitInstance
import ru.nikolas_snek.kinopoiskapi.data.network.toFullFilm
import ru.nikolas_snek.kinopoiskapi.data.network.toShortFilms
import ru.nikolas_snek.kinopoiskapi.doimain.models.FullFilm
import ru.nikolas_snek.kinopoiskapi.doimain.models.ShortFilms
import ru.nikolas_snek.kinopoiskapi.doimain.repository.Repository
import ru.nikolas_snek.kinopoiskapi.doimain.repository.RequestResult

class RepositoryImpl : Repository {
    companion object {
        private const val X_API_KEY = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"
    }

    private val retrofitInstance = RetrofitInstance.retrofit.create(KinopoiskAPI::class.java)
    override suspend fun  getAllFilms(): RequestResult<List<ShortFilms>>{
        return try {
            RequestResult.Success(
                retrofitInstance.getPopularFilms(apiKey = X_API_KEY, page = 1).films.map {
                    it.toShortFilms()
                }
            )
        }catch (e: Exception) {
            RequestResult.Error(e)
        }
    }


    override suspend fun getFilmDetailInfo(filmId: Int): RequestResult<FullFilm> {
         return try {
             RequestResult.Success(
             retrofitInstance.getFullFilm(filmId = filmId, apiKey = X_API_KEY).toFullFilm()
             )
         } catch (e: Exception) {
             RequestResult.Error(e)
         }
    }

}