package com.cmaina.network.providers

import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideRetrofit(): Retrofit {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .addInterceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader("Authorization", "Client-ID pbq2xfRl6EbYjlRQeGfkp5dBfdzSuETZQiBPrbSSswk")
            chain.proceed(request.build())
        }
        .build()
    return Retrofit.Builder()
        .baseUrl("https://api.unsplash.com/")
        .client(okHttpClient)
        .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
