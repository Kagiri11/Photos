package com.cmaina.repository.sources

import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.models.photostats.DomainPhotoStatistics
import com.cmaina.domain.models.search.PhotoSearchResultDomainModel
import com.cmaina.domain.models.specificphoto.SpecificPhotoDomainModel
import com.cmaina.domain.repository.PhotosRepository
import com.cmaina.network.api.PhotosRemoteSource
import com.cmaina.repository.mappers.toDomain
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class PhotosRepositoryImpl(private val photosRemoteSource: PhotosRemoteSource) : PhotosRepository {
    /*override suspend fun fetchPhotos(): Flow<PagingData<List<DomainPhotoListItem>>> {
        val pagingConfig = PagingConfig(pageSize = 30)
        val photosPager = Pager(pagingConfig) {
            PhotosPagingSource(photosRemoteSource = photosRemoteSource)
        }.flow
        return photosPager
    }*/

    override suspend fun fetchPhotos(): Flow<List<DomainPhotoListItem>> {
        return when (val result = photosRemoteSource.fetchPhotos(page = 4)) {
            is ApiResponse.Success -> {
                flowOf(result.data.toDomain())
            }
            is ApiResponse.Failure.Error -> {
                error(result.message())
            }
            is ApiResponse.Failure.Exception -> {
                flowOf()
            }
        }
    }

    override suspend fun getRandomPhoto(): Flow<DomainPhotoListItem> {
        return when (val response = photosRemoteSource.fetchRandomPhoto()) {
            is ApiResponse.Success -> flowOf(response.data.toDomain())
            else -> flowOf()
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
