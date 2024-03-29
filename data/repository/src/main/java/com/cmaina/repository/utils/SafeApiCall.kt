package com.cmaina.repository.utils

import com.cmaina.domain.utils.Result
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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
            else -> Result.Error(errorDetails = "") // TODO: Pass correct error message
        }
    }
}

