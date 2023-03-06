package com.cmaina.repository.sources

import com.cmaina.domain.PhotosRepository
import com.cmaina.domain.models.DomainPhoto
import com.cmaina.domain.utils.NetworkResult
import com.cmaina.local.daos.PhotoEntityDao
import com.cmaina.local.models.PhotoEntity
import com.cmaina.network.NetworkService
import com.cmaina.repository.mappers.toDomain
import com.cmaina.repository.mappers.toEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class PhotosRepositoryImpl(
    private val networkService: NetworkService,
    private val photoEntityDao: PhotoEntityDao
) : PhotosRepository {

    override suspend fun getMarsPhotosFromNetwork(): Flow<NetworkResult<List<DomainPhoto>>> {
        val apiResponse = networkService.fetchPhotos()
        return try {
            if (apiResponse.isSuccessful) {
                val photos = apiResponse.body()!!.photos
                flowOf(
                    NetworkResult.Success(photos.map { it.toDomain() })
                )
            } else {
                flowOf(NetworkResult.Error(""))
            }
        } catch (e: Exception) {
            flowOf(NetworkResult.Error("error here"))
        }
    }

    suspend fun synchronizeDataFromNetAndDatabase(photosFromDb: List<PhotoEntity>) {
        val remotePhotos = networkService.fetchPhotos().body()!!.photos

        val newPhotos = remotePhotos.filter { remotePhoto ->
            photosFromDb.none { it.id == remotePhoto.id }
        }.map { it.toEntity() }

        val updatedPhotos = remotePhotos.filter { remotePhoto ->
            val localPhoto = photosFromDb.find { it.id == remotePhoto.id }
            localPhoto != null && localPhoto != remotePhoto.toEntity()
        }.map { it.toEntity() }

        if (newPhotos.isNotEmpty()) {
            photoEntityDao.addPhotos(newPhotos)
        }

        if (updatedPhotos.isNotEmpty()) {
            photoEntityDao.addPhotos(updatedPhotos)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun fetchMarsPhotos(): Flow<List<DomainPhoto>> {
        return photoEntityDao.fetchPhotos().flatMapLatest { photosFromDb ->
            synchronizeDataFromNetAndDatabase(photosFromDb)
            photoEntityDao.fetchPhotos().map {
                it.map { it.toDomain() }
            }
        }
    }
}
