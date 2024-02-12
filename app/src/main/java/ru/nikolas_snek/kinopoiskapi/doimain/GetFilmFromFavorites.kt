package ru.nikolas_snek.kinopoiskapi.doimain

import ru.nikolas_snek.kinopoiskapi.doimain.repository.Repository

class GetFilmFromFavorites(
    private val repository: Repository
){
    suspend operator fun invoke() = repository.getFilmFromFavorites()
}