package com.cmaina.repository.paging

import androidx.paging.PagingSource.LoadParams.Refresh
import androidx.paging.PagingSource.LoadResult.Page
import com.cmaina.network.api.PhotosRemoteSource
import com.cmaina.network.models.photos.PhotoListItem
import com.cmaina.repository.mappers.toDomain
import com.google.common.truth.Truth.assertThat
import com.skydoves.sandwich.ApiResponse
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class PhotosPagingSourceTest {

    // System under test
    private lateinit var photosPagingSource: PhotosPagingSource

    // helpers
    private lateinit var mockPhotosRemoteSource: PhotosRemoteSource
    private val photoListItem = PhotoListItem(
        alt_description = "",
        blur_hash = "",
        categories = listOf(),
        color = "",
        created_at = null,
        current_user_collections = null,
        description = "cars on the street",
        height = 100,
        id = "qwerty",
        liked_by_user = false,
        likes = 123,
        links = null,
        promoted_at = null,
        sponsorship = null,
        updated_at = null,
        urls = null,
        user = null,
        width = 100,
        topic_submissions = null,
        exif = null,
        location = null,
        views = null,
        downloads = 12
    )
    private val apiResponse = mockk<ApiResponse.Success<List<PhotoListItem>>>()

    @Before
    fun setup() = runBlocking {
        mockPhotosRemoteSource = mockk()

        photosPagingSource = PhotosPagingSource(mockPhotosRemoteSource)
    }

    @Test
    fun loadReturnsPageWhenOnSuccessfulLoadOfItemKeyedData() = runBlocking {
        every { apiResponse.data } returns listOf(photoListItem)
        coEvery { mockPhotosRemoteSource.fetchPhotos(1) } returns apiResponse
        assertThat(
            photosPagingSource.load(
                Refresh(
                    key = 1,
                    loadSize = 2,
                    placeholdersEnabled = false
                )
            )
        ).isEqualTo(
            Page(
                data = listOf(photoListItem.toDomain()),
                prevKey = null,
                nextKey = 2
            )
        )
    }

    @Test
    fun loadReturnsOnNextNullOnEmptyData() = runBlocking {
        every { apiResponse.data } returns emptyList()
        coEvery { mockPhotosRemoteSource.fetchPhotos(1) } returns apiResponse
        assertThat(
            photosPagingSource.load(
                Refresh(
                    key = 1,
                    loadSize = 2,
                    placeholdersEnabled = false
                )
            )
        ).isEqualTo(
            Page(
                emptyList(),
                prevKey = null,
                nextKey = null
            )
        )
    }
}
