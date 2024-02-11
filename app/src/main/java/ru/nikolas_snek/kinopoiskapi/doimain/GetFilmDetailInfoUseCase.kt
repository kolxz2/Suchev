package ru.nikolas_snek.kinopoiskapi.doimain

import ru.nikolas_snek.kinopoiskapi.doimain.repository.Repository

class GetFilmDetailInfoUseCase(
    private val repository: Repository,
    private val filmId: Int
) {
    suspend operator fun invoke() = repository.getFilmDetailInfo(filmId)
}