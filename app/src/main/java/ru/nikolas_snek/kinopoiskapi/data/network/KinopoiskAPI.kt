package ru.nikolas_snek.kinopoiskapi.data.network

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface KinopoiskAPI {

    @GET("films/{filmId}")
    suspend fun getFullFilm(
        @Header("X-API-KEY") apiKey: String?,
        @Path("filmId") filmId: Int,
    ): FullFilmDto

    @GET("films/top?type=TOP_100_POPULAR_FILMS")
    suspend fun getPopularFilms(
        @Header("X-API-KEY") apiKey: String?,
        @Query("page") page: Int,
    ): ShortResponse
}