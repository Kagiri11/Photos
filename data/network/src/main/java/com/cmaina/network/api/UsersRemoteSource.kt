package com.cmaina.network.api

import com.cmaina.network.utils.Constants.BASEURL
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class UsersRemoteSource(private val client: HttpClient) {

    suspend fun getUser(username: String) = client.get("${BASEURL}users/$username")

    suspend fun getUserPhotos(
        username: String,
        page: Int
    ) = client.get("${BASEURL}users/$username/photos"){
        url{
            parameters.append("page", "$page")
        }
    }
}