package com.cmaina.repository.sources

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.models.photostats.DomainPhotoStatistics
import com.cmaina.domain.models.specificphoto.SpecificPhotoDomainModel
import com.cmaina.domain.repository.PhotosRepository
import com.cmaina.domain.utils.Result
import com.cmaina.network.api.PhotosRemoteSource
import com.cmaina.network.models.photos.PhotoListItem
import com.cmaina.network.models.photostats.PhotoStatistics
import com.cmaina.network.models.specificphoto.SpecificPhoto
import com.cmaina.repository.mappers.toDomain
import com.cmaina.repository.paging.PhotosPagingSource
import com.cmaina.repository.paging.SearchedPhotosPagingSource
import com.cmaina.repository.utils.InOut
import io.ktor.client.call.body
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PhotosRepositoryImpl(
    private val photosRemoteSource: PhotosRemoteSource
) : PhotosRepository {

    override suspend fun fetchPhotos(): Result<Flow<PagingData<DomainPhotoListItem>>> {
        val pagingConfig = PagingConfig(pageSize = 30)
        val photosPager = Pager(pagingConfig) {
            PhotosPagingSource(photosRemoteSource = photosRemoteSource)
        }.flow
        return Result.Success(photosPager)
    }

    override suspend fun getRandomPhoto(): Result<DomainPhotoListItem> {
        val call = photosRemoteSource.fetchRandomPhoto()
        return InOut<PhotoListItem, DomainPhotoListItem>(
            call.body()
        ).apiCall(
            response = call,
            mapper = { it.toDomain() }
        )
    }


    override suspend fun getSpecificPhoto(photoId: String): Result<SpecificPhotoDomainModel> {
        val call = photosRemoteSource.fetchPhoto(photoId)
        return InOut<SpecificPhoto, SpecificPhotoDomainModel>(
            call.body()
        ).apiCall(
            response = call,
            mapper = { it.toDomain() }
        )
    }

    override suspend fun getPhotoStatistics(photoId: String): Flow<Result<DomainPhotoStatistics>> {
        val call = photosRemoteSource.fetchPhotoStatistics(photoId)
        return flowOf(
            InOut<PhotoStatistics, DomainPhotoStatistics>(call.body())
                .apiCall(
                    response = call,
                ) {
                    it.toDomain()
                }
        )
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

    override suspend fun likePhoto(id: String): Result<DomainPhotoListItem> {
        val call = photosRemoteSource.likePhoto(id = id)
        return InOut<PhotoListItem, DomainPhotoListItem>(call.body())
            .apiCall(
                response = call,
            ) {
                it.toDomain()
            }
    }
}
