package com.cmaina.repository.sources

import com.cmaina.domain.PhotosRepository
import com.cmaina.domain.models.DomainPhoto
import com.cmaina.domain.utils.NetworkResult
import com.cmaina.network.NetworkService
import com.cmaina.repository.mappers.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PhotosRepositoryImpl(private val networkService: NetworkService) : PhotosRepository {

    override suspend fun fetchMarsPhotos(): Flow<NetworkResult<List<DomainPhoto>>> {
        val apiResponse = networkService.fetchPhotos()
        return try {
            if (apiResponse.isSuccessful) {
                flowOf(NetworkResult.Success(apiResponse.body()!!.map { it.toDomain() }))
            } else {
                flowOf(NetworkResult.Error(""))
            }
        } catch (e: Exception) {
            flowOf(NetworkResult.Error("error here"))
        }
    }
}
