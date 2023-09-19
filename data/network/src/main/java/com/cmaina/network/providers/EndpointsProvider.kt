package com.cmaina.network.providers

import com.cmaina.network.api.AuthNetworkSource
import com.cmaina.network.api.NetworkClient
import com.cmaina.network.api.PhotosNetworkSource
import com.cmaina.network.api.PhotosRemoteSource
import com.cmaina.network.api.UsersNetworkSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun providePhotosNetworkSource(retrofit: Retrofit): PhotosNetworkSource = retrofit
    .create(PhotosNetworkSource::class.java)

val photosRemoteSource = PhotosRemoteSource(client = NetworkClient)



fun provideUsersRemoteSource(retrofit: Retrofit): UsersNetworkSource =
    retrofit.create(UsersNetworkSource::class.java)

fun provideAuthRemoteSource(): AuthNetworkSource =
    Retrofit.Builder()
        .baseUrl("https://unsplash.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AuthNetworkSource::class.java)
