package com.cmaina.network.api

import com.cmaina.network.models.photos.PhotoListItem
import com.cmaina.network.models.photostats.PhotoStatistics
import com.cmaina.network.models.search.PhotoSearchResultDto
import com.cmaina.network.models.specificphoto.SpecificPhoto
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface PhotosRemoteSource {
    @GET("photos")
    suspend fun fetchPhotos(
        @Query("page") page: Int
    ): ApiResponse<List<PhotoListItem>>

    @GET("photos/{id}")
    suspend fun fetchPhoto(
        @Path("id") id: String,
        @Header("Authorization") authorization: String = "Client-ID pbq2xfRl6EbYjlRQeGfkp5dBfdzSuETZQiBPrbSSswk"
    ): ApiResponse<SpecificPhoto>

    @GET("photos/random")
    suspend fun fetchRandomPhoto(
        @Header("Authorization") authorization: String = "Client-ID pbq2xfRl6EbYjlRQeGfkp5dBfdzSuETZQiBPrbSSswk"
    ): ApiResponse<PhotoListItem>

    @GET("photos/{id}/like")
    suspend fun likePhoto(
        @Path("id") id: String,
        @Header("Authorization") authorization: String = "Client-ID pbq2xfRl6EbYjlRQeGfkp5dBfdzSuETZQiBPrbSSswk"
    ): ApiResponse<PhotoListItem>

    @GET("photos/{id}/statistics")
    suspend fun fetchPhotoStatistics(
        @Path("id") id: String,
        @Header("Authorization") authorization: String = "Client-ID pbq2xfRl6EbYjlRQeGfkp5dBfdzSuETZQiBPrbSSswk"
    ): ApiResponse<PhotoStatistics>

    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") searchQuery: String,
    ): ApiResponse<PhotoSearchResultDto>
}
