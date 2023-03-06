package com.cmaina.repository.sources

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cmaina.domain.models.CameraDomain
import com.cmaina.domain.models.DomainPhoto
import com.cmaina.domain.models.RoverDomain
import com.cmaina.domain.utils.NetworkResult
import com.cmaina.local.daos.PhotoEntityDao
import com.cmaina.local.models.CameraEntity
import com.cmaina.local.models.PhotoEntity
import com.cmaina.local.models.RoverEntity
import com.cmaina.network.NetworkService
import com.cmaina.network.models.MarsResponse
import com.cmaina.network.models.NetworkCamera
import com.cmaina.network.models.NetworkPhoto
import com.cmaina.network.models.NetworkRover
import com.cmaina.repository.mappers.toEntity
import com.google.common.truth.Truth
import io.mockk.* // ktlint-disable no-wildcard-imports
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class PhotosRepositoryImplTest {

    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val networkService: NetworkService = mockk(relaxed = true)

    private lateinit var photoEntityDao: PhotoEntityDao

    private lateinit var photosRepositoryImpl: PhotosRepositoryImpl
    private val netPhoto =
        NetworkPhoto(0, NetworkCamera(0, "", "", 0), "", "", NetworkRover(0, "", "", "", ""), 0)
    private val dbPhotos = listOf(
        PhotoEntity(
            id = 1,
            cameraEntity = CameraEntity(0, "", "", 1),
            earthDate = "url1",
            roverEntity = RoverEntity(0, "", "", "", ""),
            imgSrc = "",
            sol = 0
        ),
        PhotoEntity(
            id = 2,
            cameraEntity = CameraEntity(0, "", "", 1),
            earthDate = "url1",
            roverEntity = RoverEntity(0, "", "", "", ""),
            imgSrc = "",
            sol = 0
        ),
        PhotoEntity(
            id = 3,
            cameraEntity = CameraEntity(0, "", "", 1),
            earthDate = "url1",
            roverEntity = RoverEntity(0, "", "", "", ""),
            imgSrc = "",
            sol = 0
        )
    )

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        photoEntityDao = PhotoEntityDaoImpl()
        photosRepositoryImpl = PhotosRepositoryImpl(networkService, photoEntityDao)
    }

    @Test
    fun `getMarsPhotosFromNetwork returns error when API response is not successful`() =
        runTest {
            // Given
            coEvery { networkService.fetchPhotos() } returns mockk {
                every { isSuccessful } returns false
            }

            // When
            val result = photosRepositoryImpl.getMarsPhotosFromNetwork()

            // Then
            result.collect {
                assertEquals(NetworkResult.Error(""), it)
            }
        }

    @Test
    fun `test synchronizeDataFromNetAndDatabase with empty list`() = runTest {
        // given
        val photosFromDb = emptyList<PhotoEntity>()
        coEvery { networkService.fetchPhotos().body() } returns MarsResponse(emptyList())

        // when
        photosRepositoryImpl.synchronizeDataFromNetAndDatabase(
            photosFromDb
        )

        // then
        Truth.assertThat(photoEntityDao.fetchPhotos().first().size).isEqualTo(0)
    }

    @Test
    fun `test synchronizeDataFromNetAndDatabase with new photos`() = runTest {
        // given
        val networkService = mockk<NetworkService>()
        coEvery { networkService.fetchPhotos() } returns Response.success(MarsResponse(listOf(netPhoto)))

        // when
        photosRepositoryImpl.synchronizeDataFromNetAndDatabase(dbPhotos)

        // then
        Truth.assertThat(photoEntityDao.fetchPhotos().first().size).isEqualTo(1)
    }

    @Test
    fun `test synchronizeDataFromNetAndDatabase with updated photos`() = runBlockingTest {
        // given
        coEvery { networkService.fetchPhotos().body() } returns MarsResponse(listOf(netPhoto))

        // when
        photosRepositoryImpl.synchronizeDataFromNetAndDatabase(dbPhotos)

        // then
        Truth.assertThat(photoEntityDao.fetchPhotos().first().size).isEqualTo(2)
    }

    @Test
    fun `test synchronizeDataFromNetAndDatabase with both new and updated photos`() =
        runTest {
            // given
            coEvery { networkService.fetchPhotos().body() } returns MarsResponse(
                listOf(
                    netPhoto,
                    netPhoto.copy(id = 4)
                )
            )

            // when
            photosRepositoryImpl.synchronizeDataFromNetAndDatabase(dbPhotos)

            // then
            Truth.assertThat(photoEntityDao.fetchPhotos().first().size).isEqualTo(2)
        }
}
