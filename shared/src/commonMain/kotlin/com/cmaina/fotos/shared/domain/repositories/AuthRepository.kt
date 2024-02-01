package com.cmaina.fotos.shared.domain.repositories

import com.cmaina.fotos.shared.domain.models.auth.AuthDomainResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun authenticateUser(authCode: String): Result<com.cmaina.fotos.shared.domain.models.auth.AuthDomainResponse>

    suspend fun saveUserAuthentication(accessToken: String)

    suspend fun clearStaleUserAuthentication()

    suspend fun checkIfUserHasBeenAuthenticated(): Flow<Boolean>
}
