package com.cmaina.network

import com.cmaina.network.models.MarsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("rovers/curiosity/photos")
    suspend fun fetchPhotos(
        @Query("sol") sol: Int = 1000,
        // @Query("camera") camera: String = "fhaz",
        @Query("api_key") apiKey: String = "DEMO_KEY"
    ): Response<MarsResponse>
}
