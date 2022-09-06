package com.cmaina.domain.repository

import com.cmaina.domain.models.auth.AuthDomainResponse
import com.cmaina.domain.utils.NetworkResult

interface AuthRepository {
    suspend fun authenticateUser(authCode: String): NetworkResult<AuthDomainResponse>
}
