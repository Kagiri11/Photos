package com.cmaina.presentation.di

import androidx.paging.PagingData
import com.cmaina.domain.models.photos.Photo
import com.cmaina.domain.models.photostats.DomainPhotoStatistics
import com.cmaina.domain.models.specificphoto.SpecificPhotoDomainModel
import com.cmaina.domain.repository.PhotosRepository
import com.cmaina.domain.utils.Result
import com.cmaina.presentation.screens.home.HomeViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { HomeViewModel(get()) }
    single<PhotosRepository> { FakePhotosRepository() }
}

class FakePhotosRepository : PhotosRepository {
    override suspend fun fetchPhotos(): Flow<PagingData<Photo>> {
        return flowOf()
    }

    override suspend fun getRandomPhoto(): Result<Photo> {
        TODO("Not yet implemented")
    }

    override suspend fun getSpecificPhoto(photoId: String): Result<SpecificPhotoDomainModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getPhotoStatistics(photoId: String): Flow<Result<DomainPhotoStatistics>> {
        TODO("Not yet implemented")
    }

    override suspend fun searchPhoto(searchString: String): Flow<PagingData<Photo>> {
        TODO("Not yet implemented")
    }

    override suspend fun likePhoto(id: String): Result<Photo> {
        TODO("Not yet implemented")
    }
}
