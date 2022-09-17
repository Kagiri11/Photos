package com.cmaina.domain.repository

import com.cmaina.domain.models.auth.AuthDomainResponse
import com.cmaina.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun authenticateUser(authCode: String): NetworkResult<AuthDomainResponse>

    suspend fun saveUserAuthentication()

    suspend fun clearStaleUserAuthentication()

    suspend fun checkIfUserHasBeenAuthenticated(): Flow<Boolean>
}
