package com.cmaina.network.api

import com.cmaina.network.models.auth.AuthRemoteResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthNetworkSource {

    @POST("oauth/token")
    suspend fun authorizeUser(
        @Query("client_id") clientId: String = "pbq2xfRl6EbYjlRQeGfkp5dBfdzSuETZQiBPrbSSswk",
        @Query("client_secret") clientSecret: String = "mj232Q-hkm9J3Ufyn5EJ5I0q4yYs_hkPp85udjOqo4c",
        @Query("redirect_uri") redirectUri: String = "fotos://callback",
        @Query("code") code: String,
        @Query("grant_type") grantType: String = "authorization_code"
    ): AuthRemoteResponse
}
