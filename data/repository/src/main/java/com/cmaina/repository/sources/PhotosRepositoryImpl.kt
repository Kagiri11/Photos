package com.cmaina.repository.sources

import android.util.Log
import com.cmaina.domain.models.photos.DomainPhotoList
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.models.photostats.DomainPhotoStatistics
import com.cmaina.domain.models.search.PhotoSearchResultDomainModel
import com.cmaina.domain.models.specificphoto.SpecificPhotoDomainModel
import com.cmaina.domain.repository.PhotosRepository
import com.cmaina.network.api.PhotosRemoteSource
import com.cmaina.repository.mappers.toDomain
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.onSuccess
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class PhotosRepositoryImpl(private val photosRemoteSource: PhotosRemoteSource) : PhotosRepository {
    override suspend fun fetchPhotos(): Flow<DomainPhotoList> {
        val response = photosRemoteSource.fetchPhotos()
        return flow {
            response.onSuccess {
                Log.d("PhotosCollected", "This is the data collected: $data")
                data.toDomain()
            }.onError {
                errorBody
            }.onFailure {
            }
        }
    }

    override suspend fun getRandomPhoto(): Flow<DomainPhotoListItem> {
        return flow {
            val response = photosRemoteSource.fetchRandomPhoto()
            response.suspendOnSuccess {
                emit(data.toDomain())
            }.suspendOnError {
                message()
            }.suspendOnException {
                message()
            }
        }
    }

    override suspend fun getSpecificPhoto(photoId: String): Flow<SpecificPhotoDomainModel> {
        return flow {
            photosRemoteSource.fetchPhoto(photoId)
                .suspendOnSuccess {
                }
                .suspendOnError {
                }
                .suspendOnException {
                }
        }
    }

    override suspend fun getPhotoStatistics(): Flow<DomainPhotoStatistics> {
        return flowOf()
    }

    override suspend fun searchPhoto(): Flow<PhotoSearchResultDomainModel> {
        return flowOf()
    }
}
