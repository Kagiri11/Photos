package com.cmaina.presentation.screens.home

import androidx.paging.PagingData
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.models.photostats.DomainPhotoStatistics
import com.cmaina.domain.models.specificphoto.SpecificPhotoDomainModel
import com.cmaina.domain.repository.PhotosRepository
import com.cmaina.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Before

class HomeViewModelTest {
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        val photosRepository = FakePhotosRepository()
        homeViewModel = HomeViewModel(photosRepository)
    }

    /*@Test
    fun `fetchPhoto collects images from repository`() = runBlocking {
    }*/
}

class FakePhotosRepository : PhotosRepository {
    override suspend fun fetchPhotos(): Flow<PagingData<DomainPhotoListItem>> {
        return flowOf()
    }

    override suspend fun getRandomPhoto(): NetworkResult<DomainPhotoListItem> {
        val fakeDomainPhotoListItem = DomainPhotoListItem(
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
        return NetworkResult.Error("")
    }

    override suspend fun getSpecificPhoto(photoId: String): NetworkResult<SpecificPhotoDomainModel> {
        return NetworkResult.Error("")
    }

    override suspend fun getPhotoStatistics(photoId: String): Flow<NetworkResult<DomainPhotoStatistics>> {
        return flowOf()
    }

    override suspend fun searchPhoto(searchString: String): Flow<PagingData<DomainPhotoListItem>> {
        return flowOf()
    }
}
