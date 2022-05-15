package com.cmaina.repository.di

import com.cmaina.domain.repository.PhotosRepository
import com.cmaina.repository.sources.PhotosRepositoryImpl
import org.koin.core.scope.get
import org.koin.dsl.module

val repositoryModule = module {
    single<PhotosRepository> { PhotosRepositoryImpl(get()) }
}
