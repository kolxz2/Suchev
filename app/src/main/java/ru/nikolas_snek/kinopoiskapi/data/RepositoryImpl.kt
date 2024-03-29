package ru.nikolas_snek.kinopoiskapi.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import ru.nikolas_snek.kinopoiskapi.data.database.FilmDao
import ru.nikolas_snek.kinopoiskapi.data.database.FilmEntity
import ru.nikolas_snek.kinopoiskapi.data.network.FullFilmDto
import ru.nikolas_snek.kinopoiskapi.data.network.KinopoiskAPI
import ru.nikolas_snek.kinopoiskapi.data.network.RetrofitInstance
import ru.nikolas_snek.kinopoiskapi.data.network.toFullFilm
import ru.nikolas_snek.kinopoiskapi.doimain.models.FullFilm
import ru.nikolas_snek.kinopoiskapi.doimain.models.ShortFilms
import ru.nikolas_snek.kinopoiskapi.doimain.repository.Repository
import ru.nikolas_snek.kinopoiskapi.doimain.repository.RequestResult
import java.io.ByteArrayOutputStream
import java.net.HttpURLConnection
import java.net.URL

class RepositoryImpl(
    private val dao: FilmDao,
) : Repository {
    companion object {
        private const val X_API_KEY = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"

        const val PAGE_SIZE = 20
    }

    private val retrofitInstance = RetrofitInstance.retrofit.create(KinopoiskAPI::class.java)


    override suspend fun getAllFilms(): Flow<PagingData<ShortFilms>> {
        return Pager(PagingConfig(pageSize = PAGE_SIZE)) {
            // todo получать ошибки  при отправлении
            FilmsPagingSource(retrofitInstance, dao)
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
        withContext(Dispatchers.IO){
            val film = retrofitInstance.getFullFilm(filmId = filmId, apiKey = X_API_KEY)
            val byteArray = downloadImage(film)
            val countries = film.countries.map { it.country }
            val genres = film.genres.map { it.genre }
            val filmBD = FilmEntity(
                filmId = film.kinopoiskId,
                nameRu = film.nameRu,
                year = film.year.toString(),
                posterUrlPreview = film.posterUrl,
                imageData = byteArray,
                description = film.description,
                countries = countries.joinToString(separator = ", ") ,
                genres = genres.joinToString(separator = ", ")
            )
            dao.addFilm(filmBD)
        }

    }

    private fun downloadImage(film: FullFilmDto): ByteArray {
        val connection = URL(film.posterUrl).openConnection() as HttpURLConnection
        connection.doInput = true
        connection.connect()
        val inputStream = connection.inputStream
        // Преобразуйте входной поток в Bitmap
        val bitmap = BitmapFactory.decodeStream(inputStream)
        // Преобразуйте Bitmap в массив байтов
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        val byteArray = stream.toByteArray()
        inputStream.close()
        connection.disconnect()
        return byteArray
    }

    override suspend fun getFilmFromFavorites(): List<FilmEntity> =
        dao.getFilms()

}