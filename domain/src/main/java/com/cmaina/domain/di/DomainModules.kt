package com.cmaina.domain.di

import com.cmaina.domain.usecases.FetchPhotoStatisticsUseCase
import com.cmaina.domain.usecases.FetchPhotosUseCase
import com.cmaina.domain.usecases.FetchRandomPhotoUseCase
import com.cmaina.domain.usecases.FetchSpecificPhotoUseCase
import com.cmaina.domain.usecases.FetchUserPhotosUseCase
import com.cmaina.domain.usecases.FetchUserPortFolioUseCase
import com.cmaina.domain.usecases.FetchUserProfileUseCase
import org.koin.dsl.module

val domainModules = module {
    single { FetchPhotosUseCase(get()) }
    single { FetchPhotoStatisticsUseCase(get()) }
    single { FetchRandomPhotoUseCase(get()) }
    single { FetchUserProfileUseCase(get()) }
    single { FetchSpecificPhotoUseCase(get()) }
    single { FetchUserPhotosUseCase(get()) }
    single { FetchUserPortFolioUseCase(get()) }
}
