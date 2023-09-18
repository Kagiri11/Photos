package com.cmaina.repository.utils

import com.cmaina.domain.models.specificphoto.SpecificPhotoDomainModel
import com.cmaina.domain.utils.NetworkResult
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

suspend fun <T : Any> flowSafeApiCall(
    apiCall: suspend () -> T
): Flow<NetworkResult<T>> = flow {
    try {
        emit(NetworkResult.Success(data = apiCall.invoke()))
    } catch (e: Exception) {
        emit(NetworkResult.Error(errorDetails = e.message.toString()))
    }
}

suspend fun <T : Any> safeApiCall(
    apiCall: suspend () -> T
) =
    try {
        NetworkResult.Success(data = apiCall.invoke())
    } catch (e: Exception) {
        NetworkResult.Error(errorDetails = e.message.toString())
    }

class InOut<in T : Any, out R : Any>(val httpResponse: HttpResponse, val item: @UnsafeVariance T) {

    suspend fun apiCall(
        mapper: suspend (@UnsafeVariance T) -> @UnsafeVariance R
    ): com.cmaina.repository.utils.NetworkResult<@UnsafeVariance R> {

        return when (httpResponse.status.value) {
            200 -> com.cmaina.repository.utils.NetworkResult.Success(data = mapper.invoke(item))
            else -> com.cmaina.repository.utils.NetworkResult.Error(httpResponse.status.value, message = "")
        }
    }
}

