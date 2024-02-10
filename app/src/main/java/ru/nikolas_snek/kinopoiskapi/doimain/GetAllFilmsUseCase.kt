package ru.nikolas_snek.kinopoiskapi.doimain

import ru.nikolas_snek.kinopoiskapi.doimain.models.ShortFilms
import ru.nikolas_snek.kinopoiskapi.doimain.repository.Repository
import ru.nikolas_snek.kinopoiskapi.doimain.repository.RequestResult

class GetAllFilmsUseCase(
    private val repository: Repository
)  {
    suspend operator fun invoke(): RequestResult<List<ShortFilms>> = repository.getAllFilms()
}