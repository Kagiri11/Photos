package com.cmaina.network

import com.cmaina.network.models.NetworkPhoto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    // Remember to remove the static rover and use a dynamic one
    @GET("rovers/curiosity/photos")
    suspend fun fetchPhotos(
        @Query("sol") sol: Int = 1000,
        @Query("camera") camera: String = "fhaz",
        @Query("api_key") apiKey: String = "DEMO_KEY"
    ): Response<List<NetworkPhoto>>
}
