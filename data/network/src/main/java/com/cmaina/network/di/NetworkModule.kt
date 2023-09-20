package com.cmaina.network.di

import com.cmaina.network.providers.authRemoteSource
import com.cmaina.network.providers.photosRemoteSource
import com.cmaina.network.providers.provideRetrofit
import com.cmaina.network.providers.usersRemoteSource
import org.koin.dsl.module

val networkModule = module {
    single { provideRetrofit() }
    factory { photosRemoteSource }
    factory { usersRemoteSource }
    factory { authRemoteSource }
}
