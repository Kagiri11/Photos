package com.cmaina.fotos.shared.data.network

import io.ktor.client.statement.HttpResponse


suspend inline fun <T : Any, reified R : Any>safeApiCall(
    apiCall: suspend () -> T
) =
    try {
        Result.Success(data = apiCall.invoke())
    } catch (e: Exception) {
        Result.Error(errorDetails = e.message.toString())
    }

class InOut<in T : Any, out R : Any>(val item: @UnsafeVariance T) {

    suspend fun apiCall(
        response: HttpResponse,
        mapper: suspend (@UnsafeVariance T) -> @UnsafeVariance R
    ): Result<@UnsafeVariance R> {

        return when (response.status.value) {
            200 -> Result.Success(data = mapper.invoke(item))
            else -> Result.Error(errorDetails = "")
        }
    }
}

