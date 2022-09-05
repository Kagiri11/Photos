package com.cmaina.network.providers

import com.cmaina.network.api.AuthRemoteSource
import com.cmaina.network.api.PhotosRemoteSource
import com.cmaina.network.api.UsersRemoteSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun providePhotosRemoteSource(retrofit: Retrofit): PhotosRemoteSource = retrofit
    .create(PhotosRemoteSource::class.java)

fun provideUsersRemoteSource(retrofit: Retrofit): UsersRemoteSource =
    retrofit.create(UsersRemoteSource::class.java)

fun provideAuthRemoteSource(): AuthRemoteSource =
    Retrofit.Builder()
        .baseUrl("https://unsplash.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AuthRemoteSource::class.java)
