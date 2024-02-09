package ru.nikolas_snek.kinopoiskapi.data.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface KinopoiskAPI {

    @GET("api/v2.2/films/{filmId}")
    suspend fun getFullFilm(
        @Path("filmId") studentHash: String,
    ): Call<FullFilmsDto>

    @GET("v2.2/films/top")
    suspend fun getShortFilms(
        @Query("type") type: String,
        @Query("page") page: Int
    ): Call<List<ShortFilmDto>>
}