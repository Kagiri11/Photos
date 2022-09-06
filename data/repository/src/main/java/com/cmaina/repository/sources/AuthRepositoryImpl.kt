package com.cmaina.repository.sources

import com.cmaina.domain.models.auth.AuthDomainResponse
import com.cmaina.domain.repository.AuthRepository
import com.cmaina.domain.utils.NetworkResult
import com.cmaina.network.api.AuthRemoteSource
import com.cmaina.repository.mappers.toDomain
import com.cmaina.repository.utils.safeApiCall

class AuthRepositoryImpl(private val authRemoteSource: AuthRemoteSource) : AuthRepository {
    override suspend fun authenticateUser(authCode: String): NetworkResult<AuthDomainResponse> {
        return safeApiCall { authRemoteSource.authorizeUser(code = authCode).toDomain() }
    }
}
