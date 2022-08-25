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
import kotlinx.coroutines.flow.Flow

class PhotosRepositoryImpl(private val photosRemoteSource: PhotosRemoteSource) : PhotosRepository {

    override suspend fun fetchPhotos(): Flow<PagingData<DomainPhotoListItem>> {
        val pagingConfig = PagingConfig(pageSize = 30)
        val photosPager = Pager(pagingConfig) {
            PhotosPagingSource(photosRemoteSource = photosRemoteSource)
        }.flow
        return photosPager
    }

    override suspend fun getRandomPhoto(): Flow<NetworkResult<DomainPhotoListItem>> =
        flowSafeApiCall { photosRemoteSource.fetchRandomPhoto().toDomain() }

    override suspend fun getSpecificPhoto(photoId: String): Flow<NetworkResult<SpecificPhotoDomainModel>> =
        flowSafeApiCall { photosRemoteSource.fetchPhoto(photoId).toDomain() }

    override suspend fun getPhotoStatistics(photoId: String): Flow<NetworkResult<DomainPhotoStatistics>> =
        flowSafeApiCall { photosRemoteSource.fetchPhotoStatistics(photoId).toDomain() }

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
