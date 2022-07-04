package com.cmaina.domain.di

import com.cmaina.domain.usecases.FetchPhotoStatisticsUseCase
import com.cmaina.domain.usecases.FetchPhotosUseCase
import com.cmaina.domain.usecases.FetchRandomPhotoUseCase
import com.cmaina.domain.usecases.FetchSpecificPhotoUseCase
import com.cmaina.domain.usecases.FetchUserPhotosUseCase
import com.cmaina.domain.usecases.FetchUserPortFolioUseCase
import com.cmaina.domain.usecases.FetchUserProfileUseCase
import com.cmaina.domain.usecases.FetchUserUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModules = module {
    singleOf(::FetchPhotosUseCase)
    singleOf(::FetchPhotoStatisticsUseCase)
    singleOf(::FetchRandomPhotoUseCase)
    singleOf(::FetchSpecificPhotoUseCase)
    singleOf(::FetchUserPhotosUseCase)
    singleOf(::FetchUserProfileUseCase)
    singleOf(::FetchUserPortFolioUseCase)
    singleOf(::FetchUserUseCase)
}
