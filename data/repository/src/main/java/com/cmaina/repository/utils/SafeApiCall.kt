package com.cmaina.repository.utils

import com.cmaina.domain.utils.NetworkResult
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
