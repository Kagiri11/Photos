package com.cmaina.network.providers

import com.cmaina.network.api.PhotosRemoteSource
import retrofit2.Retrofit

fun providePhotosRemoteSource(retrofit: Retrofit) = retrofit
    .create(PhotosRemoteSource::class.java)
