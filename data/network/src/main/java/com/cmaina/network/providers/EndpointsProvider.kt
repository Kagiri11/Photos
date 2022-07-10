package com.cmaina.network.providers

import com.cmaina.network.api.PhotosRemoteSource
import com.cmaina.network.api.UsersRemoteSource
import retrofit2.Retrofit

fun providePhotosRemoteSource(retrofit: Retrofit): PhotosRemoteSource = retrofit
    .create(PhotosRemoteSource::class.java)

fun provideUsersRemoteSource(retrofit: Retrofit): UsersRemoteSource =
    retrofit.create(UsersRemoteSource::class.java)
