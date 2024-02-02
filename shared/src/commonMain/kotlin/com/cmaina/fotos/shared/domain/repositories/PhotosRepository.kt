package com.cmaina.fotos.shared.domain.repositories

import app.cash.paging.PagingData
import com.cmaina.fotos.shared.domain.models.photos.Photo
import com.cmaina.fotos.shared.domain.models.photostats.DomainPhotoStatistics
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {

    suspend fun fetchPhotos(): com.cmaina.fotos.shared.domain.utils.Result<Flow<PagingData<Photo>>>

    suspend fun getRandomPhoto(): com.cmaina.fotos.shared.domain.utils.Result<Photo>

    suspend fun getSpecificPhoto(photoId: String): com.cmaina.fotos.shared.domain.utils.Result<Photo>

    suspend fun fetchUserPhotos(username: String): Flow<androidx.paging.PagingData<Photo>>

    suspend fun getPhotoStatistics(photoId: String): Flow<com.cmaina.fotos.shared.domain.utils.Result<com.cmaina.fotos.shared.domain.models.photostats.DomainPhotoStatistics>>

    suspend fun searchPhoto(searchString: String): Flow<PagingData<Photo>>

    suspend fun likePhoto(id: String): com.cmaina.fotos.shared.domain.utils.Result<Photo>
}