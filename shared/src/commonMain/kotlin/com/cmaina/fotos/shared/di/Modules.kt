package com.cmaina.fotos.shared.di

import com.cmaina.fotos.shared.data.network.sources.authRemoteSource
import com.cmaina.fotos.shared.data.network.sources.photosRemoteSource
import com.cmaina.fotos.shared.data.network.sources.usersRemoteSource
import com.cmaina.fotos.shared.data.cache.utils.provideDataStore
import com.cmaina.fotos.shared.data.repositories.AppRepositoryImpl
import com.cmaina.fotos.shared.data.repositories.AuthRepositoryImpl
import com.cmaina.fotos.shared.data.repositories.PhotosRepositoryImpl
import com.cmaina.fotos.shared.data.repositories.UsersRepositoryImpl
import com.cmaina.fotos.shared.domain.repositories.AppRepository
import com.cmaina.fotos.shared.domain.repositories.AuthRepository
import com.cmaina.fotos.shared.domain.repositories.PhotosRepository
import com.cmaina.fotos.shared.domain.repositories.UsersRepository
import com.cmaina.fotos.shared.presentation.screens.home.HomeViewModel
import com.cmaina.fotos.shared.presentation.screens.photodetails.PhotoDetailsViewModel
import com.cmaina.fotos.shared.presentation.screens.settings.SettingsViewModel
import com.cmaina.fotos.shared.presentation.screens.user.UserViewModel
import com.cmaina.fotos.shared.utils.AppSettings
import org.koin.dsl.module

val networkModule = module {
    factory { photosRemoteSource }
    factory { usersRemoteSource }
    factory { authRemoteSource }
}

val dataModule = module {
    single { provideDataStore(get()) }
}

val repositoryModule = module {
    single<PhotosRepository> { PhotosRepositoryImpl(photosRemoteSource = get()) }
    factory<UsersRepository> { UsersRepositoryImpl(usersRemoteSource = get()) }
    single<AuthRepository> { AuthRepositoryImpl(authRemoteSource = get(), preferences = get()) }
    factory<AppRepository> { AppRepositoryImpl(settings = AppSettings) }
}

val presentationModule = module {
    factory { HomeViewModel(photosRepository = get()) }
    factory { PhotoDetailsViewModel(photosRepository = get(), authRepository = get()) }
    factory { SettingsViewModel(appRepository = get()) }
    factory { UserViewModel(usersRepository = get(), photosRepository = get()) }
}