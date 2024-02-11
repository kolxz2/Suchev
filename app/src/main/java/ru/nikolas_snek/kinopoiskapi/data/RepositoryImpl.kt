package ru.nikolas_snek.kinopoiskapi.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.nikolas_snek.kinopoiskapi.data.database.FilmDao
import ru.nikolas_snek.kinopoiskapi.data.database.FilmEntity
import ru.nikolas_snek.kinopoiskapi.data.network.KinopoiskAPI
import ru.nikolas_snek.kinopoiskapi.data.network.RetrofitInstance
import ru.nikolas_snek.kinopoiskapi.data.network.toFullFilm
import ru.nikolas_snek.kinopoiskapi.doimain.models.FullFilm
import ru.nikolas_snek.kinopoiskapi.doimain.models.ShortFilms
import ru.nikolas_snek.kinopoiskapi.doimain.repository.Repository
import ru.nikolas_snek.kinopoiskapi.doimain.repository.RequestResult

class RepositoryImpl(
    private val dao: FilmDao
) : Repository {
    companion object {
        private const val X_API_KEY = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"

        const val PAGE_SIZE = 20
    }

    private val retrofitInstance = RetrofitInstance.retrofit.create(KinopoiskAPI::class.java)


    override suspend fun getAllFilms(): Flow<PagingData<ShortFilms>> {
        return Pager(PagingConfig(pageSize = PAGE_SIZE)) {
            // todo получать ошибки  при отправлении
            FilmsPagingSource(retrofitInstance)
        }.flow

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

    override suspend fun saveToFavorites(filmId: Int) {
        val film = retrofitInstance.getFullFilm(filmId = filmId, apiKey = X_API_KEY)
        val filmBD = FilmEntity(
            filmId=film.kinopoiskId,
            nameRu=film.nameRu,
                    year=film.year.toString(),
                    posterUrlPreview=film.posterUrl,
        )
        dao.addFilm(filmBD)
    }

}