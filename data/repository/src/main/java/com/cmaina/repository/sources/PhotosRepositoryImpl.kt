package com.cmaina.repository.sources

import android.util.Log
import com.cmaina.domain.PhotosRepository
import com.cmaina.domain.models.DomainPhoto
import com.cmaina.domain.utils.NetworkResult
import com.cmaina.local.daos.PhotoEntityDao
import com.cmaina.network.NetworkService
import com.cmaina.repository.mappers.toDomain
import com.cmaina.repository.mappers.toEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class PhotosRepositoryImpl(
    private val networkService: NetworkService,
    private val photoEntityDao: PhotoEntityDao,
    coroutineScope: CoroutineScope
) : PhotosRepository {

    init {
        coroutineScope.launch {
            collectPhotosFromNetwork()
        }
    }

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

    suspend fun collectPhotosFromNetwork() {
        getMarsPhotosFromNetwork().collect {
            when (it) {
                is NetworkResult.Success -> {
                    it.data.forEach {
                        Log.d("Addition", "Adding this $it")
                        photoEntityDao.addPhoto(it.toEntity())
                    }
                }
                is NetworkResult.Error -> {}
            }
        }
    }

    override suspend fun fetchMarsPhotosFromLocalSource(): Flow<List<DomainPhoto>> {
        return photoEntityDao.fetchPhotos().map { it.map { it.toDomain() } }
    }
}
