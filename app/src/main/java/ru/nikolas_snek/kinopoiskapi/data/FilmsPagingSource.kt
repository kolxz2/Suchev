package ru.nikolas_snek.kinopoiskapi.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.nikolas_snek.kinopoiskapi.data.network.KinopoiskAPI
import ru.nikolas_snek.kinopoiskapi.data.network.toShortFilms
import ru.nikolas_snek.kinopoiskapi.doimain.models.ShortFilms

class FilmsPagingSource(
    private val retrofitInstance: KinopoiskAPI,
) : PagingSource<Int, ShortFilms>() {

    companion object {
        private const val X_API_KEY = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"
    }


    override fun getRefreshKey(state: PagingState<Int, ShortFilms>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ShortFilms> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response =
                retrofitInstance.getPopularFilms(apiKey = X_API_KEY, page = nextPageNumber)
            val data = response.films.map {
                it.toShortFilms()
            }
            LoadResult.Page(
                data = data,
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = if (response.films.isEmpty()) null else nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}