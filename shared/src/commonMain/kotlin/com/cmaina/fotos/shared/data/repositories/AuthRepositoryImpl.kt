package com.cmaina.fotos.shared.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.cmaina.fotos.shared.data.Constants.UserAccessToken
import com.cmaina.fotos.shared.data.mappers.toDomain
import com.cmaina.fotos.shared.data.network.AuthRemoteSource
import com.cmaina.fotos.shared.data.network.InOut
import com.cmaina.fotos.shared.domain.models.auth.AuthDomainResponse
import com.cmaina.fotos.shared.domain.repositories.AuthRepository
import com.cmaina.fotos.shared.data.network.models.auth.AuthRemoteResponse
import com.cmaina.fotos.shared.domain.utils.Result
import com.cmaina.fotos.shared.utils.AppSettings
import io.ktor.client.call.body
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthRepositoryImpl(
    private val authRemoteSource: AuthRemoteSource,
    private val preferences: DataStore<Preferences>,
) : AuthRepository {

    private val userAuthenticatedPref = booleanPreferencesKey("userAuthenticated")

    override suspend fun authenticateUser(authCode: String): Result<AuthDomainResponse> {
        val call = authRemoteSource.authorizeUser(code = authCode)
        return InOut<AuthRemoteResponse, AuthDomainResponse>(call.body())
            .apiCall(response = call) { it.toDomain() }
    }

    override suspend fun saveUserAuthentication(accessToken: String) {
        AppSettings.putString(key = "access_token", accessToken)
        AppSettings.putBoolean(key = "isUserAuthenticated", true)
    }

    override suspend fun clearStaleUserAuthentication() {
        preferences.edit {
            it[userAuthenticatedPref] = false
        }
    }

    override suspend fun checkIfUserHasBeenAuthenticated(): Flow<Boolean> {
        return preferences.data.map {
            it[userAuthenticatedPref] ?: false
        }
    }
}
