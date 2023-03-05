package com.cmaina.repository.sources

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cmaina.domain.PhotosRepository
import com.cmaina.domain.models.CameraDomain
import com.cmaina.domain.models.DomainPhoto
import com.cmaina.domain.models.RoverDomain
import com.cmaina.domain.utils.NetworkResult
import com.cmaina.local.daos.PhotoEntityDao
import com.cmaina.local.models.CameraEntity
import com.cmaina.local.models.PhotoEntity
import com.cmaina.local.models.RoverEntity
import com.cmaina.network.NetworkService
import io.mockk.* // ktlint-disable no-wildcard-imports
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PhotosRepositoryImplTest {

    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var networkService: NetworkService

    @MockK
    private lateinit var photoEntityDao: PhotoEntityDao

    @MockK
    private lateinit var coroutineScope: CoroutineScope
    private lateinit var photosRepository: PhotosRepository
    private val testPhotos = listOf(
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

    private val testDomainPhotos = listOf(
        DomainPhoto(
            id = 1,
            cameraDomain = CameraDomain(0, "", "", 1),
            earthDate = "url1",
            roverDomain = RoverDomain(0, "", "", "", ""),
            imgSrc = "",
            sol = 0
        ),
        DomainPhoto(
            id = 2,
            cameraDomain = CameraDomain(0, "", "", 1),
            earthDate = "url1",
            roverDomain = RoverDomain(0, "", "", "", ""),
            imgSrc = "",
            sol = 0
        ),
        DomainPhoto(
            id = 3,
            cameraDomain = CameraDomain(0, "", "", 1),
            earthDate = "url1",
            roverDomain = RoverDomain(0, "", "", "", ""),
            imgSrc = "",
            sol = 0
        )
    )

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        photosRepository = PhotosRepositoryImpl(networkService, photoEntityDao, coroutineScope)
    }

    @Test
    fun `test getMarsPhotosFromNetwork returns error when API response is not successful`() =
        runBlocking {
            // Given
            coEvery { networkService.fetchPhotos() } returns mockk {
                every { isSuccessful } returns false
            }

            // When
            val result = photosRepository.getMarsPhotosFromNetwork()

            // Then
            result.collect {
                assertEquals(NetworkResult.Error(""), it)
            }
        }
}
