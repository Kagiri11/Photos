package com.cmaina.domain.repository

import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.models.photostats.DomainPhotoStatistics
import com.cmaina.network.models.specificphoto.SpecificPhoto
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {

    suspend fun getRandomPhoto(): Flow<DomainPhotoListItem>

    suspend fun getSpecificPhoto(): Flow<SpecificPhoto>

    suspend fun getPhotoStatistics(): Flow<DomainPhotoStatistics>
}
