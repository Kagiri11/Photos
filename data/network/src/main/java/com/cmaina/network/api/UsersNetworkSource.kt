package com.cmaina.network.api

import com.cmaina.network.models.photos.PhotoListItem
import com.cmaina.network.models.users.UserDto
import com.cmaina.network.models.users.portfolio.UserPortFolioDto
import com.cmaina.network.models.users.statistics.UserStatistics
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface UsersNetworkSource {
    @GET("users/{username}")
    suspend fun getUser(
        @Path("username") username: String,
    ): UserDto

    @GET("users/{username}/portfolio")
    suspend fun getUserPortFolio(
        @Path("username") username: String,
    ): UserPortFolioDto

    @GET("users/{username}/photos")
    suspend fun getUserPhotos(
        @Path("username") username: String,
        @Query("page") page: Int
    ): List<PhotoListItem>

    @GET("users/{username}/statistics")
    suspend fun getUserStatistics(
        @Path("username") username: String,
        @Header("Authorization") authorization: String = "Client-ID pbq2xfRl6EbYjlRQeGfkp5dBfdzSuETZQiBPrbSSswk"
    ): UserStatistics
}
