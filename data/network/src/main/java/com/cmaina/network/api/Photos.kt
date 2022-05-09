package com.cmaina.network.api

import com.cmaina.network.models.PhotoListItem
import com.cmaina.network.models.specificphoto.SpecificPhoto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface Photos {
    @GET("photos")
    suspend fun fetchPhotos(
        @Header("Authorization") authorization: String = "Client-ID pbq2xfRl6EbYjlRQeGfkp5dBfdzSuETZQiBPrbSSswk"
    ): Response<List<String>>

    @GET("photos/{id}")
    suspend fun fetchPhoto(
        @Path("id") id: String,
        @Header("Authorization") authorization: String = "Client-ID pbq2xfRl6EbYjlRQeGfkp5dBfdzSuETZQiBPrbSSswk"
    ): Response<SpecificPhoto>

    @GET("photos/random")
    suspend fun fetchRandomPhoto(
        @Header("Authorization") authorization: String = "Client-ID pbq2xfRl6EbYjlRQeGfkp5dBfdzSuETZQiBPrbSSswk"
    ): Response<PhotoListItem>

    @GET("photos/{id}/like")
    suspend fun likePhoto(
        @Path("id") id: String,
        @Header("Authorization") authorization: String = "Client-ID pbq2xfRl6EbYjlRQeGfkp5dBfdzSuETZQiBPrbSSswk"
    ): Response<PhotoListItem>
}
