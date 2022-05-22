package com.cmaina.network.di

import com.cmaina.network.providers.providePhotosRemoteSource
import com.cmaina.network.providers.provideRetrofit
import org.koin.dsl.module

val networkModule = module {
    single { provideRetrofit() }
    factory { providePhotosRemoteSource(get()) }
}
