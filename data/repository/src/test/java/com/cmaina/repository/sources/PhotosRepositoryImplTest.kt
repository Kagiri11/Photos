package com.cmaina.repository.sources

import com.cmaina.domain.utils.Result
import com.cmaina.network.api.PhotosNetworkSource
import com.cmaina.repository.utils.DomainPhotoListItem
import com.cmaina.repository.utils.PhotoListItem
import com.cmaina.repository.utils.SpecificDomainPhoto
import com.cmaina.repository.utils.SpecificPhoto
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class PhotosRepositoryImplTest {

    // utilities
    private val photosNetworkSource = mockk<PhotosNetworkSource>()

    // SUT
    private lateinit var photosRepositoryImpl: PhotosRepositoryImpl

    @Before
    fun setup() {
        coEvery { photosNetworkSource.fetchRandomPhoto() } returns PhotoListItem
        coEvery { photosNetworkSource.fetchPhoto("") } returns SpecificPhoto
        photosRepositoryImpl = PhotosRepositoryImpl(photosNetworkSource)
    }

    @Test
    fun checkThatGetRandomPhotoReturnsARandomDomainPhoto() = runBlocking {
        assertThat(photosRepositoryImpl.getRandomPhoto()).isEqualTo(Result.Success(data = DomainPhotoListItem))
    }

    @Test
    fun checkThatGetSpecificPhotoReturnsASpecificDomainPhoto() = runBlocking {
        assertThat(photosRepositoryImpl.getSpecificPhoto("")).isEqualTo(Result.Success(data = SpecificDomainPhoto))
    }
}
