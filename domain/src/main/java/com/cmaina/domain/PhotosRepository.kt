package com.cmaina.domain

import com.cmaina.domain.models.DomainPhoto
import com.cmaina.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {

    suspend fun fetchMarsPhotos(): Flow<NetworkResult<List<DomainPhoto>>>
}
