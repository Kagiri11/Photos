package com.cmaina.network.di

import com.cmaina.network.providers.providePhotosRemoteSource
import com.cmaina.network.providers.provideRetrofit
import com.cmaina.network.providers.provideUsersRemoteSource
import org.koin.dsl.module

val networkModule = module {
    single { provideRetrofit() }
    factory { providePhotosRemoteSource(get()) }
    factory { provideUsersRemoteSource(get()) }
}
