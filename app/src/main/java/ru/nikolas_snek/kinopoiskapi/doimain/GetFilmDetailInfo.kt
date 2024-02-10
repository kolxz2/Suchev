package ru.nikolas_snek.kinopoiskapi.doimain

class GetFilmDetailInfo(
    private val repository: Repository
) {
    suspend operator fun invoke(filmId: Int) = repository.getFilmDetailInfo(filmId)
}