package com.cmaina.domain

import com.cmaina.domain.models.DomainPhoto
import com.cmaina.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {

    suspend fun getMarsPhotosFromNetwork(): Flow<NetworkResult<List<DomainPhoto>>>

    fun fetchMarsPhotosFromLocalSource(): Flow<List<DomainPhoto>>
}
