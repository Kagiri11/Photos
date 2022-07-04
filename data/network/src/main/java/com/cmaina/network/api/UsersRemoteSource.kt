package com.cmaina.network.api

import com.cmaina.network.models.photos.PhotoList
import com.cmaina.network.models.users.UserDto
import com.cmaina.network.models.users.portfolio.UserPortFolioDto
import com.cmaina.network.models.users.statistics.UserStatistics
import com.skydoves.sandwich.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface UsersRemoteSource {
    @GET("users/{username}")
    fun getUser(
        @Path("username") username: String,
        @Header("Authorization") authorization: String = "Client-ID pbq2xfRl6EbYjlRQeGfkp5dBfdzSuETZQiBPrbSSswk"
    ): ApiResponse<UserDto>

    @GET("users/{username}/portfolio")
    fun getUserPortFolio(
        @Path("username") username: String,
        @Header("Authorization") authorization: String = "Client-ID pbq2xfRl6EbYjlRQeGfkp5dBfdzSuETZQiBPrbSSswk"
    ): Response<UserPortFolioDto>

    @GET("users/{username}/photos")
    fun getUserPhotos(
        @Path("username") username: String,
        @Header("Authorization") authorization: String = "Client-ID pbq2xfRl6EbYjlRQeGfkp5dBfdzSuETZQiBPrbSSswk"
    ): Response<PhotoList>

    @GET("users/{username}/statistics")
    fun getUserStatistics(
        @Path("username") username: String,
        @Header("Authorization") authorization: String = "Client-ID pbq2xfRl6EbYjlRQeGfkp5dBfdzSuETZQiBPrbSSswk"
    ): Response<UserStatistics>
}
