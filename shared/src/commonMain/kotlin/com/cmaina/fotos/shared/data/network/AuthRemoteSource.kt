package com.cmaina.fotos.shared.data.network

import com.cmaina.fotos.shared.data.Constants.BASEURL
import io.ktor.client.HttpClient
import io.ktor.client.request.post

class AuthRemoteSource(private val client: HttpClient) {

    suspend fun authorizeUser(
        clientId: String = "pbq2xfRl6EbYjlRQeGfkp5dBfdzSuETZQiBPrbSSswk",
        clientSecret: String = "mj232Q-hkm9J3Ufyn5EJ5I0q4yYs_hkPp85udjOqo4c",
        redirectUri: String = "fotos://callback",
        code: String,
        grantType: String = "authorization_code"
    ) = client.post("${BASEURL}oauth/token") {
        url {
            parameters.append("client_id", clientId)
            parameters.append("client_secret", clientSecret)
            parameters.append("redirect_uri", redirectUri)
            parameters.append("code", code)
            parameters.append("grant_type", grantType)
        }
    }
}