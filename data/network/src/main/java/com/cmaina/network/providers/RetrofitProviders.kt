package com.cmaina.network.providers

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.runBlocking

val UserAccessToken = stringPreferencesKey("accessToken")

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
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
