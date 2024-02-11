package ru.nikolas_snek.kinopoiskapi.doimain

import ru.nikolas_snek.kinopoiskapi.doimain.repository.Repository

class GetAllFilmsUseCase(
    private val repository: Repository
)  {
    suspend operator fun invoke() = repository.getAllFilms()
}