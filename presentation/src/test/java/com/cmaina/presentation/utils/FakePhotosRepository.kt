package com.cmaina.presentation.utils

import androidx.paging.PagingData
import com.cmaina.domain.models.photos.Photo
import com.cmaina.domain.models.photostats.DomainPhotoStatistics
import com.cmaina.domain.models.specificphoto.SpecificPhotoDomainModel
import com.cmaina.domain.repository.PhotosRepository
import com.cmaina.domain.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakePhotosRepository : PhotosRepository {
    override suspend fun fetchPhotos(): Result<Flow<PagingData<Photo>>> {
        return Result.Success(flowOf())
    }

    override suspend fun getRandomPhoto(): Result<Photo> {
        val fakePhoto = Photo(
            altDescription = "",
            blurHash = "",
            categories = listOf(),
            color = "",
            created_at = null,
            currentUserCollections = null,
            description = "cars on the street",
            height = 100,
            id = "qwerty",
            likedByUser = false,
            likes = 123,
            linksDomain = null, promotedAt = null,
            sponsorshipDomainModel = null,
            updatedAt = null,
            domainUrls = null,
            domainPhotoUser = null, width = 100
        )
        return Result.Success(fakePhoto)
    }

    override suspend fun getSpecificPhoto(photoId: String): Result<SpecificPhotoDomainModel> {
        return Result.Error("")
    }

    override suspend fun getPhotoStatistics(photoId: String): Flow<Result<DomainPhotoStatistics>> {
        return flowOf()
    }

    override suspend fun searchPhoto(searchString: String): Flow<PagingData<Photo>> {
        return flowOf()
    }

    override suspend fun likePhoto(id: String): Result<Photo> {
        return Result.Error("")
    }
}