package com.cmaina.network.di

import com.cmaina.network.providers.photosRemoteSource
import com.cmaina.network.providers.provideAuthRemoteSource
import com.cmaina.network.providers.providePhotosNetworkSource
import com.cmaina.network.providers.provideRetrofit
import com.cmaina.network.providers.provideUsersRemoteSource
import org.koin.dsl.module

val networkModule = module {
    single { provideRetrofit() }
    factory { providePhotosNetworkSource(get()) }
    factory {  photosRemoteSource }
    factory { provideUsersRemoteSource(get()) }
    factory { provideAuthRemoteSource() }
}
