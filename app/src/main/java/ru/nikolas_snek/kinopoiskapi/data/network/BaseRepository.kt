package ru.nikolas_snek.kinopoiskapi.data.network

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {

    suspend fun <T> safeApiCall(
        apiCall: () -> Unit
    ): ResultRequest<T> {
        return withContext(Dispatchers.IO) {
            try {
                ResultRequest.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        ResultRequest.Error(throwable.response()?.errorBody())
                    }

                    else -> {
                        Log.d("NET throwable ", throwable.message.toString() )
                        ResultRequest.Error(null)
                    }
                }

            }
        }

    }
}