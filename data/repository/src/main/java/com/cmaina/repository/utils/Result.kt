package com.cmaina.repository.utils

import com.cmaina.domain.utils.Result

sealed class NetworkResult<T : Any> : Result<T>{
    data class Success<T: Any>(val data: T) : NetworkResult<T>()
    class Error<T: Any>(val code: Int, val message: String?) : NetworkResult<T>()
    class Exception<T: Any>(val e: Throwable) : NetworkResult<T>()
}
