package com.cmaina.fotos.shared.domain.utils

sealed class Result<out T : Any> {
    data class Error(val errorDetails: String) : Result<Nothing>()
    data class Success<out T : Any>(val data: T) : Result<T>()
}
