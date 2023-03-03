package com.cmaina.network

import com.cmaina.network.models.NetworkPhoto
import retrofit2.Response
import retrofit2.http.GET

interface NetworkService {

    // Remember to remove the static rover and use a dynamic one
    @GET("rovers/curiosity/photos")
    suspend fun fetchPhotos(): Response<List<NetworkPhoto>>
}
