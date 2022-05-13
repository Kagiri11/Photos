package com.cmaina.domain.di

import com.cmaina.domain.usecases.FetchPhotoStatistics
import com.cmaina.domain.usecases.FetchPhotos
import com.cmaina.domain.usecases.FetchRandomPhoto
import com.cmaina.domain.usecases.FetchSpecificPhoto
import com.cmaina.domain.usecases.FetchUserPhotos
import com.cmaina.domain.usecases.FetchUserPortFolio
import com.cmaina.domain.usecases.FetchUserProfile
import org.koin.dsl.module

val domainModules = module {
    single { FetchPhotos(get()) }
    single { FetchPhotoStatistics(get()) }
    single { FetchRandomPhoto(get()) }
    single { FetchUserProfile(get()) }
    single { FetchSpecificPhoto(get()) }
    single { FetchUserPhotos(get()) }
    single { FetchUserPortFolio(get()) }
}
