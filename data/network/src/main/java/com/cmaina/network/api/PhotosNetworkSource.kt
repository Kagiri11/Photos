package com.cmaina.network.api

import com.cmaina.network.models.photos.PhotoListItem
import com.cmaina.network.models.photostats.PhotoStatistics
import com.cmaina.network.models.search.PhotoSearchResultDto
import com.cmaina.network.models.specificphoto.SpecificPhoto
import io.ktor.client.request.get
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PhotosNetworkSource {
    @GET("photos")
    suspend fun fetchPhotos(
        @Query("page") page: Int
    ): List<PhotoListItem>

    @GET("photos/{id}")
    suspend fun fetchPhoto(
        @Path("id") id: String,
    ): SpecificPhoto

    @GET("photos/random")
    suspend fun fetchRandomPhoto(): PhotoListItem

    @POST("photos/{id}/like")
    suspend fun likePhoto(
        @Path("id") id: String,
    ): PhotoListItem

    @GET("photos/{id}/statistics")
    suspend fun fetchPhotoStatistics(
        @Path("id") id: String,
    ): PhotoStatistics

    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") searchQuery: String,
        @Query("page") page: Int
    ): PhotoSearchResultDto
}
