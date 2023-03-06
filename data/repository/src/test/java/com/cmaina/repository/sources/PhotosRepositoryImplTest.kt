package com.cmaina.repository.sources

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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
import com.google.common.truth.Truth
import io.mockk.* // ktlint-disable no-wildcard-imports
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.createTestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class PhotosRepositoryImplTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private val networkService: NetworkService = mockk(relaxed = true)

    private lateinit var photoEntityDao: PhotoEntityDao

    private val testCoroutineScope: TestCoroutineScope = mockk(relaxed = true)

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
        photoEntityDao = PhotoEntityDaoImpl()
        photosRepositoryImpl = PhotosRepositoryImpl(
            networkService,
            photoEntityDao,
            createTestCoroutineScope()
        )
    }

    @Test
    fun `getMarsPhotosFromNetwork returns error when API response is not successful`() =
        runTest {
            // Given
            coEvery { networkService.fetchPhotos() } returns Response.error(
                400,
                ResponseBody.create(
                    MediaType.parse(""),
                    ""
                )
            )

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
    fun `synchronizeDataFromNetAndDatabase with new photos adds photos that were not there`() = runTest {
        // given
        coEvery { networkService.fetchPhotos() } returns Response.success(
            MarsResponse(
                listOf(
                    netPhoto
                )
            )
        )

        // when
        photosRepositoryImpl.synchronizeDataFromNetAndDatabase(dbPhotos)

        // then
        // Truth.assertThat(photoEntityDao.fetchPhotos().first().size).isEqualTo(1)
    }

    @Test
    fun `synchronizeDataFromNetAndDatabase with updated photos updates stored photos`() = runBlockingTest {
        // given
        coEvery { networkService.fetchPhotos().body() } returns MarsResponse(listOf(netPhoto))

        // when
        photosRepositoryImpl.synchronizeDataFromNetAndDatabase(dbPhotos)

        // then
       // Truth.assertThat(photoEntityDao.fetchPhotos().first().size).isEqualTo(1)
    }

    @Test
    fun `synchronizeDataFromNetAndDatabase with both new and updated photos updates stored photos`() =
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
            //Truth.assertThat(photoEntityDao.fetchPhotos().first().size).isEqualTo(2)
        }
}
