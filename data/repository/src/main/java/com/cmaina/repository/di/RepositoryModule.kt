package com.cmaina.repository.di

import com.cmaina.domain.PhotosRepository
import com.cmaina.repository.sources.PhotosRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module

val RepositoryModule = module {
    factory { SupervisorJob() }
    factory { CoroutineScope(Dispatchers.IO) }
    factory<PhotosRepository> { PhotosRepositoryImpl(get(), get(), get()) }
}
