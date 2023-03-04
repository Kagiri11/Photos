package com.cmaina.repository.sources

import com.cmaina.domain.PhotosRepository
import com.cmaina.domain.models.DomainPhoto
import com.cmaina.domain.utils.NetworkResult
import com.cmaina.local.daos.PhotoEntityDao
import com.cmaina.network.NetworkService
import com.cmaina.repository.mappers.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class PhotosRepositoryImpl(
    private val networkService: NetworkService,
    private val photoEntityDao: PhotoEntityDao
) : PhotosRepository {

    override suspend fun getMarsPhotosFromNetwork(): Flow<NetworkResult<List<DomainPhoto>>> {
        val apiResponse = networkService.fetchPhotos()

        return try {
            if (apiResponse.isSuccessful) {
                flowOf(
                    NetworkResult.Success(apiResponse.body()!!.photos.map { it.toDomain() })
                )
            } else {
                flowOf(NetworkResult.Error(""))
            }
        } catch (e: Exception) {
            flowOf(NetworkResult.Error("error here"))
        }
    }

    override fun fetchMarsPhotosFromLocalSource(): Flow<List<DomainPhoto>> {
        return photoEntityDao.fetchPhotos().map { it.map { it.toDomain() } }
    }
}
