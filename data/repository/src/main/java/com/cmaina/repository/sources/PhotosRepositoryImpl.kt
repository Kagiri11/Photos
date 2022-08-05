package com.cmaina.repository.sources

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.models.photostats.DomainPhotoStatistics
import com.cmaina.domain.models.search.PhotoSearchResultDomainModel
import com.cmaina.domain.models.specificphoto.SpecificPhotoDomainModel
import com.cmaina.domain.repository.PhotosRepository
import com.cmaina.network.api.PhotosRemoteSource
import com.cmaina.repository.mappers.toDomain
import com.cmaina.repository.paging.PhotosPagingSource
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PhotosRepositoryImpl(private val photosRemoteSource: PhotosRemoteSource) : PhotosRepository {

    override suspend fun fetchPhotos(): Flow<PagingData<DomainPhotoListItem>> {
        val pagingConfig = PagingConfig(pageSize = 30)
        val photosPager = Pager(pagingConfig) {
            PhotosPagingSource(photosRemoteSource = photosRemoteSource)
        }.flow
        return photosPager
    }

    override suspend fun getRandomPhoto(): Flow<DomainPhotoListItem> {
        return when (val response = photosRemoteSource.fetchRandomPhoto()) {
            is ApiResponse.Success -> flowOf(response.data.toDomain())
            else -> flowOf()
        }
    }

    override suspend fun getSpecificPhoto(photoId: String): Flow<SpecificPhotoDomainModel> {
        return when (val result = photosRemoteSource.fetchPhoto(photoId)) {
            is ApiResponse.Success -> flowOf(result.data.toDomain())
            is ApiResponse.Failure.Error -> flowOf()
            is ApiResponse.Failure.Exception -> flowOf()
        }
    }

    override suspend fun getPhotoStatistics(): Flow<DomainPhotoStatistics> {
        return flowOf()
    }

    override suspend fun searchPhoto(searchString: String): Flow<PhotoSearchResultDomainModel> {
        return when (val result = photosRemoteSource.searchPhotos(searchQuery = searchString)){
            is ApiResponse.Success -> flowOf(result.data.toDomain())
            else -> flowOf()
        }
    }
}
