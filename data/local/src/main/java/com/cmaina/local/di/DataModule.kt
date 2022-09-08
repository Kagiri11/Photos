package com.cmaina.local.di

import com.cmaina.local.utils.provideDataStore
import org.koin.dsl.module

val dataModule = module {
    single { provideDataStore(get()) }
}
