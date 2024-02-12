package ru.nikolas_snek.kinopoiskapi.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FilmDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFilm(filmEntity: FilmEntity)


    @Query("SELECT * FROM film_entity")
    suspend fun getFilms(): List<FilmEntity>

    @Query("SELECT COUNT(*) FROM film_entity WHERE filmId = :filmId")
    suspend fun countFilmsWithId(filmId: Int): Int

}