package com.cmaina.repository.sources

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.models.photostats.DomainPhotoStatistics
import com.cmaina.domain.models.specificphoto.SpecificPhotoDomainModel
import com.cmaina.domain.repository.PhotosRepository
import com.cmaina.domain.utils.NetworkResult
import com.cmaina.network.api.PhotosRemoteSource
import com.cmaina.repository.mappers.toDomain
import com.cmaina.repository.paging.PhotosPagingSource
import com.cmaina.repository.paging.SearchedPhotosPagingSource
import com.cmaina.repository.utils.flowSafeApiCall
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class PhotosRepositoryImpl(private val photosRemoteSource: PhotosRemoteSource) : PhotosRepository {

    override suspend fun fetchPhotos(): Flow<PagingData<DomainPhotoListItem>> {
        val pagingConfig = PagingConfig(pageSize = 30)
        val photosPager = Pager(pagingConfig) {
            PhotosPagingSource(photosRemoteSource = photosRemoteSource)
        }.flow
        return photosPager
    }

    override suspend fun getRandomPhoto(): Flow<NetworkResult<DomainPhotoListItem>> {
        return  flowSafeApiCall { photosRemoteSource.fetchRandomPhoto().toDomain() }
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

    override suspend fun searchPhoto(searchString: String): Flow<PagingData<DomainPhotoListItem>> {
        val pagingConfig = PagingConfig(pageSize = 30)
        val searchedPhotosPager = Pager(pagingConfig) {
            SearchedPhotosPagingSource(
                photosRemoteSource = photosRemoteSource,
                searchString = searchString
            )
        }.flow
        return searchedPhotosPager
    }
}
