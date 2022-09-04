package com.cmaina.repository.sources

import com.cmaina.domain.utils.NetworkResult
import com.cmaina.network.api.PhotosRemoteSource
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
    private val photosRemoteSource = mockk<PhotosRemoteSource>()

    // SUT
    private lateinit var photosRepositoryImpl: PhotosRepositoryImpl

    @Before
    fun setup() {
        coEvery { photosRemoteSource.fetchRandomPhoto() } returns PhotoListItem
        coEvery { photosRemoteSource.fetchPhoto("") } returns SpecificPhoto
        photosRepositoryImpl = PhotosRepositoryImpl(photosRemoteSource)
    }

    @Test
    fun checkThatGetRandomPhotoReturnsARandomDomainPhoto() = runBlocking {
        assertThat(photosRepositoryImpl.getRandomPhoto()).isEqualTo(NetworkResult.Success(data = DomainPhotoListItem))
    }

    @Test
    fun checkThatGetSpecificPhotoReturnsASpecificDomainPhoto() = runBlocking {
        assertThat(photosRepositoryImpl.getSpecificPhoto("")).isEqualTo(NetworkResult.Success(data = SpecificDomainPhoto))
    }
}
