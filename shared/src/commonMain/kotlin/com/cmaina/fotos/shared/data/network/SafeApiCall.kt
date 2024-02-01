package com.cmaina.fotos.shared.data.network

import com.cmaina.fotos.shared.domain.utils.Result
import com.cmaina.fotos.shared.domain.utils.Result.*
import io.ktor.client.statement.HttpResponse


suspend inline fun <T : Any, reified R : Any> safeApiCall(
    apiCall: suspend () -> T
) =
    try {
        Success(data = apiCall.invoke())
    } catch (e: Exception) {
        Error(errorDetails = "")
    }

class InOut<in T : Any, out R : Any>(val item: @UnsafeVariance T) {

    suspend fun apiCall(
        response: HttpResponse,
        mapper: suspend (@UnsafeVariance T) -> @UnsafeVariance R
    ): Result<@UnsafeVariance R> {

        return when (response.status.value) {
            200 -> Success(data = mapper.invoke(item))
            else -> Error(errorDetails = "")
        }
    }
}

