package com.cmaina.repository.di

import com.cmaina.domain.repository.PhotosRepository
import com.cmaina.domain.repository.UsersRepository
import com.cmaina.repository.sources.PhotosRepositoryImpl
import com.cmaina.repository.sources.UsersRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<PhotosRepository> { PhotosRepositoryImpl(get()) }
    factory<UsersRepository> { UsersRepositoryImpl(get()) }
}
