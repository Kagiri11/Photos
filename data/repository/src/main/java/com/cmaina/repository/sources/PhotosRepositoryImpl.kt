package com.cmaina.repository.sources

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.models.photostats.DomainPhotoStatistics
import com.cmaina.domain.models.specificphoto.SpecificPhotoDomainModel
import com.cmaina.domain.repository.PhotosRepository
import com.cmaina.domain.utils.NetworkResult
import com.cmaina.network.api.PhotosNetworkSource
import com.cmaina.network.api.PhotosRemoteSource
import com.cmaina.network.models.specificphoto.SpecificPhoto
import com.cmaina.repository.mappers.toDomain
import com.cmaina.repository.paging.PhotosPagingSource
import com.cmaina.repository.paging.SearchedPhotosPagingSource
import com.cmaina.repository.utils.InOut
import com.cmaina.repository.utils.flowSafeApiCall
import com.cmaina.repository.utils.safeApiCall
import com.cmaina.repository.utils.safeApiCall2
import io.ktor.client.call.body
import kotlinx.coroutines.flow.Flow

class PhotosRepositoryImpl(
    private val photosNetworkSource: PhotosNetworkSource,
    private val photosRemoteSource: PhotosRemoteSource
) : PhotosRepository {

    override suspend fun fetchPhotos(): NetworkResult<Flow<PagingData<DomainPhotoListItem>>> {
        val pagingConfig = PagingConfig(pageSize = 30)
        val photosPager = Pager(pagingConfig) {
            PhotosPagingSource(photosNetworkSource = photosNetworkSource)
        }.flow
        return NetworkResult.Success(photosPager)
    }

    override suspend fun getRandomPhoto(): NetworkResult<DomainPhotoListItem> =
        safeApiCall { photosNetworkSource.fetchRandomPhoto().toDomain() }

    override suspend fun getSpecificPhoto(photoId: String): NetworkResult<SpecificPhotoDomainModel> =
        safeApiCall { photosNetworkSource.fetchPhoto(photoId).toDomain() }

    suspend fun getSpecificPho(photoId: String): com.cmaina.repository.utils.NetworkResult<SpecificPhotoDomainModel> =
        InOut<SpecificPhoto, SpecificPhotoDomainModel>(
            photosRemoteSource.fetchPhoto(""),
            photosRemoteSource.fetchPhoto("").body()
        ).apiCall {
            it.toDomain()
        }

    override suspend fun getPhotoStatistics(photoId: String): Flow<NetworkResult<DomainPhotoStatistics>> =
        flowSafeApiCall { photosNetworkSource.fetchPhotoStatistics(photoId).toDomain() }

    override suspend fun searchPhoto(searchString: String): Flow<PagingData<DomainPhotoListItem>> {
        val pagingConfig = PagingConfig(pageSize = 30)
        val searchedPhotosPager = Pager(pagingConfig) {
            SearchedPhotosPagingSource(
                photosNetworkSource = photosNetworkSource,
                searchString = searchString
            )
        }.flow
        return searchedPhotosPager
    }

    override suspend fun likePhoto(id: String): NetworkResult<DomainPhotoListItem> {
        return safeApiCall { photosNetworkSource.likePhoto(id).toDomain() }
    }
}
