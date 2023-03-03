package com.cmaina.network.di

import org.koin.dsl.module

val NetworkModule = module {
    single { provideRetrofit() }
    factory { provideNetworkService(get()) }
}
