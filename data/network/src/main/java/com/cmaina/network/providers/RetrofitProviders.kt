package com.cmaina.network.providers

import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideRetrofit(): Retrofit {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor { chain ->
            val request = chain.request()
            val newRequest = request.newBuilder().apply {
                addHeader("Authorization", "Client-ID pbq2xfRl6EbYjlRQeGfkp5dBfdzSuETZQiBPrbSSswk")
            }.build()
            chain.proceed(newRequest)
        }
        .build()
    return Retrofit.Builder()
        .baseUrl("https://api.unsplash.com/")
        .client(okHttpClient)
        .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
