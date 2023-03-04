package com.cmaina.repository.di

import com.cmaina.domain.PhotosRepository
import com.cmaina.repository.sources.PhotosRepositoryImpl
import org.koin.dsl.module

val RepositoryModule = module {
    factory<PhotosRepository> { PhotosRepositoryImpl(get()) }
}
