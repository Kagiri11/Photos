package com.cmaina.fotos.shared.repositories

import app.cash.paging.PagingData
import com.cmaina.fotos.shared.models.photos.Photo
import com.cmaina.fotos.shared.models.photostats.DomainPhotoStatistics
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {

    suspend fun fetchPhotos(): Result<Flow<PagingData<Photo>>>

    suspend fun getRandomPhoto(): Result<Photo>

    suspend fun getSpecificPhoto(photoId: String): Result<Photo>

    suspend fun getPhotoStatistics(photoId: String): Flow<Result<DomainPhotoStatistics>>

    suspend fun searchPhoto(searchString: String): Flow<PagingData<Photo>>

    suspend fun likePhoto(id: String): Result<Photo>
}