package com.cmaina.repository.sources

import com.cmaina.domain.utils.Result
import com.cmaina.network.Network
import com.cmaina.network.api.PhotosRemoteSource
import com.cmaina.repository.utils.DomainPhotoListItem
import com.cmaina.repository.utils.SpecificDomainPhoto
import com.google.common.truth.Truth.assertThat
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class PhotosRepositoryImplTest {

    // utilities
    private val mockEngine = MockEngine { request ->
        respond(
            content = ByteReadChannel("""{"ip":"127.0.0.1"}"""),
            status = HttpStatusCode.OK,
            headers = headersOf(HttpHeaders.ContentType, "application/json")
        )
    }
    private val testNetworkClient = Network(engine = mockEngine).httpClient
    private val testPhotosRemoteSource = PhotosRemoteSource(client = testNetworkClient)

    // SUT
    private lateinit var photosRepositoryImpl: PhotosRepositoryImpl

    @Before
    fun setup() {
        photosRepositoryImpl = PhotosRepositoryImpl(testPhotosRemoteSource)
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
