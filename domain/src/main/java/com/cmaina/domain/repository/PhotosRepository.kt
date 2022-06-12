package com.cmaina.domain.repository

import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.models.photostats.DomainPhotoStatistics
import com.cmaina.domain.models.search.PhotoSearchResultDomainModel
import com.cmaina.domain.models.specificphoto.SpecificPhotoDomainModel
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {

    //    suspend fun fetchPhotos(): Flow<PagingData<List<DomainPhotoListItem>>>
    suspend fun fetchPhotos(): Flow<List<DomainPhotoListItem>>

    suspend fun getRandomPhoto(): Flow<DomainPhotoListItem>

    suspend fun getSpecificPhoto(photoId: String): Flow<SpecificPhotoDomainModel>

    suspend fun getPhotoStatistics(): Flow<DomainPhotoStatistics>

    suspend fun searchPhoto(): Flow<PhotoSearchResultDomainModel>
}