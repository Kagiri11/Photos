package com.cmaina.repository.sources

import com.cmaina.domain.models.photos.DomainPhotoList
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.models.photostats.DomainPhotoStatistics
import com.cmaina.domain.models.search.PhotoSearchResultDomainModel
import com.cmaina.domain.models.specificphoto.SpecificPhotoDomainModel
import com.cmaina.domain.repository.PhotosRepository
import com.cmaina.network.api.PhotosRemoteSource
import com.cmaina.repository.mappers.toDomain
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class PhotosRepositoryImpl(private val photosRemoteSource: PhotosRemoteSource) : PhotosRepository {
    override suspend fun fetchPhotos(): Flow<DomainPhotoList> {
        return flow {
            val response = photosRemoteSource.fetchPhotos()
            response
                .suspendOnSuccess {
                    emit(data.toDomain())
                }
                .suspendOnError {
                    message()
                }
                .suspendOnException {
                    message()
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
                   emit()
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
