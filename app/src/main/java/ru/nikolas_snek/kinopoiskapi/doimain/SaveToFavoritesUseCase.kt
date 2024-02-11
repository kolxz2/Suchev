package ru.nikolas_snek.kinopoiskapi.doimain

import ru.nikolas_snek.kinopoiskapi.doimain.repository.Repository

class SaveToFavoritesUseCase(
    private val repository: Repository,
    private val filmId: Int
) {

    suspend operator fun invoke() = repository.saveToFavorites(filmId)
}