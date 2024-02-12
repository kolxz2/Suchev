package ru.nikolas_snek.kinopoiskapi.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "film_entity")
data class FilmEntity (
    @PrimaryKey
    val filmId: Int,
    val nameRu: String,
    val year: String,
    val description: String,
    val countries: String,
    val genres: String,
    val posterUrlPreview: String,
    val imageData: ByteArray
)

