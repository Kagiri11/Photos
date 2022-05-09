package com.cmaina.network.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface UnsplashService {
    @GET
    suspend fun fetchPhotos(
        @Header("Authorization") authorization: String = "Client-ID pbq2xfRl6EbYjlRQeGfkp5dBfdzSuETZQiBPrbSSswk"
    ): Response<List<String>>
}
