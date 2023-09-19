package com.cmaina.domain.repository

import androidx.paging.PagingData
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.models.photostats.DomainPhotoStatistics
import com.cmaina.domain.models.specificphoto.SpecificPhotoDomainModel
import com.cmaina.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {

    suspend fun fetchPhotos(): Result<Flow<PagingData<DomainPhotoListItem>>>

    suspend fun getRandomPhoto(): Result<DomainPhotoListItem>

    suspend fun getSpecificPhoto(photoId: String): Result<SpecificPhotoDomainModel>

    suspend fun getPhotoStatistics(photoId: String): Flow<Result<DomainPhotoStatistics>>

    suspend fun searchPhoto(searchString: String): Flow<PagingData<DomainPhotoListItem>>

    suspend fun likePhoto(id: String): Result<DomainPhotoListItem>
}
