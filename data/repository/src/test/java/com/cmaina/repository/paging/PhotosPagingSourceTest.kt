package com.cmaina.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadParams.Refresh
import com.cmaina.network.api.PhotosRemoteSource
import com.cmaina.network.models.photos.PhotoListItem
import com.cmaina.repository.utils.DomainPhotoListItem
import com.cmaina.repository.utils.PhotoListItem
import com.google.common.truth.Truth.assertThat
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class PhotosPagingSourceTest {

    // System under test
    private lateinit var photosPagingSource: PhotosPagingSource

    // helpers
    private lateinit var photosNetworkSource: PhotosRemoteSource
    private val photoListItem = listOf(PhotoListItem)

    @Before
    fun setup() = runBlocking {
        photosNetworkSource = mockk()
        photosPagingSource = PhotosPagingSource(photosNetworkSource)
    }

    @Test
    fun `load returns a page on successful load of item keyed data`(): Unit = runBlocking {
        coEvery { photosNetworkSource.fetchPhotos(1).body<List<PhotoListItem>>() } returns photoListItem
        val loadParam = Refresh(key = 1, loadSize = 2, placeholdersEnabled = false)
        val expectedPage =
            PagingSource.LoadResult.Page(listOf(DomainPhotoListItem), prevKey = null, nextKey = 2)
        assertThat(photosPagingSource.load(loadParam)).isEqualTo(expectedPage)
    }

    @Test
    fun `load returns on next null on empty data`(): Unit = runBlocking {
        coEvery { photosNetworkSource.fetchPhotos(1).body<List<PhotoListItem>>() } returns emptyList()
        assertThat(
            photosPagingSource.load(
                Refresh(
                    key = 1,
                    loadSize = 2,
                    placeholdersEnabled = false
                )
            )
        ).isEqualTo(
            PagingSource.LoadResult.Page(
                listOf(),
                prevKey = null,
                nextKey = null
            )
        )
    }
}
