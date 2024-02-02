package com.cmaina.fotos.shared.di

import com.cmaina.fotos.shared.data.network.sources.authRemoteSource
import com.cmaina.fotos.shared.data.network.sources.photosRemoteSource
import com.cmaina.fotos.shared.data.network.sources.usersRemoteSource
import com.cmaina.local.utils.provideDataStore
import org.koin.dsl.module

val networkModule = module {
    factory { photosRemoteSource }
    factory { usersRemoteSource }
    factory { authRemoteSource }
}

val dataModule = module {
    single { provideDataStore(get()) }
}