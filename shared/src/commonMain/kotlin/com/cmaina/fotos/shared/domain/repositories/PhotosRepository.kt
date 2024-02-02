package com.cmaina.fotos.shared.domain.repositories

import app.cash.paging.PagingData
import com.cmaina.fotos.shared.domain.models.photos.Photo
import com.cmaina.fotos.shared.domain.models.photostats.DomainPhotoStatistics
import com.cmaina.fotos.shared.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {

    suspend fun fetchPhotos(): Result<Flow<PagingData<Photo>>>

    suspend fun getRandomPhoto(): Result<Photo>

    suspend fun getSpecificPhoto(photoId: String): Result<Photo>

    suspend fun fetchUserPhotos(username: String): Flow<androidx.paging.PagingData<Photo>>

    suspend fun getPhotoStatistics(photoId: String): Flow<Result<DomainPhotoStatistics>>

    suspend fun searchPhoto(searchString: String): Flow<PagingData<Photo>>

    suspend fun likePhoto(id: String): Result<Photo>
}