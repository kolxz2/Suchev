package ru.nikolas_snek.kinopoiskapi.doimain

class GetAllFilmsUseCase(
    private val repository: Repository
)  {
    suspend operator fun invoke() = repository.getAllFilms()
}