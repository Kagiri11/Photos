package com.cmaina.fotos.shared.domain.repositories

import app.cash.paging.PagingData
import com.cmaina.fotos.shared.domain.models.photos.Photo
import com.cmaina.fotos.shared.domain.models.photostats.DomainPhotoStatistics
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {

    suspend fun fetchPhotos(): Result<Flow<PagingData<com.cmaina.fotos.shared.domain.models.photos.Photo>>>

    suspend fun getRandomPhoto(): Result<com.cmaina.fotos.shared.domain.models.photos.Photo>

    suspend fun getSpecificPhoto(photoId: String): Result<com.cmaina.fotos.shared.domain.models.photos.Photo>

    suspend fun fetchUserPhotos(username: String): Flow<androidx.paging.PagingData<com.cmaina.fotos.shared.domain.models.photos.Photo>>

    suspend fun getPhotoStatistics(photoId: String): Flow<Result<com.cmaina.fotos.shared.domain.models.photostats.DomainPhotoStatistics>>

    suspend fun searchPhoto(searchString: String): Flow<PagingData<com.cmaina.fotos.shared.domain.models.photos.Photo>>

    suspend fun likePhoto(id: String): Result<com.cmaina.fotos.shared.domain.models.photos.Photo>
}