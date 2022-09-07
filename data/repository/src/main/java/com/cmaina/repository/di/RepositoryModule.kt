package com.cmaina.repository.di

import com.cmaina.domain.repository.AppRepository
import com.cmaina.domain.repository.AuthRepository
import com.cmaina.domain.repository.PhotosRepository
import com.cmaina.domain.repository.UsersRepository
import com.cmaina.repository.sources.AppRepositoryImpl
import com.cmaina.repository.sources.AuthRepositoryImpl
import com.cmaina.repository.sources.PhotosRepositoryImpl
import com.cmaina.repository.sources.UsersRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<PhotosRepository> { PhotosRepositoryImpl(get()) }
    factory<UsersRepository> { UsersRepositoryImpl(get()) }
    factory<AuthRepository> { AuthRepositoryImpl(get()) }
    factory<AppRepository> { AppRepositoryImpl(get()) }
}
