package com.cmaina.network.api

import com.cmaina.network.models.photos.PhotoList
import com.cmaina.network.models.photos.PhotoListItem
import com.cmaina.network.models.photostats.PhotoStatistics
import com.cmaina.network.models.search.PhotoSearchResultDto
import com.cmaina.network.models.specificphoto.SpecificPhoto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface Photos {
    @GET("photos")
    suspend fun fetchPhotos(
        @Header("Authorization") authorization: String = "Client-ID pbq2xfRl6EbYjlRQeGfkp5dBfdzSuETZQiBPrbSSswk"
    ): Response<PhotoList>

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

    @GET("photos/{id}/statistics")
    suspend fun fetchPhotoStatistics(
        @Path("id") id: String,
        @Header("Authorization") authorization: String = "Client-ID pbq2xfRl6EbYjlRQeGfkp5dBfdzSuETZQiBPrbSSswk"
    ): Response<PhotoStatistics>

    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") searchQuery: String,
    ): Response<PhotoSearchResultDto>
}
