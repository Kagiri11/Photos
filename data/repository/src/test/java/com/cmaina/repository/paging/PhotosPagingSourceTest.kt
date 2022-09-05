package com.cmaina.repository.paging

import com.cmaina.network.api.PhotosRemoteSource
import com.cmaina.network.models.photos.PhotoListItem
import com.skydoves.sandwich.ApiResponse
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before

class PhotosPagingSourceTest {

    // System under test
    private lateinit var photosPagingSource: PhotosPagingSource

    // helpers
    private lateinit var mockPhotosRemoteSource: PhotosRemoteSource

    private val apiResponse = mockk<ApiResponse.Success<List<PhotoListItem>>>()

    @Before
    fun setup() = runBlocking {
        mockPhotosRemoteSource = mockk()

        photosPagingSource = PhotosPagingSource(mockPhotosRemoteSource)
    }

//    @Test
//    fun loadReturnsPageWhenOnSuccessfulLoadOfItemKeyedData() = runBlocking {
//        every { apiResponse.data } returns listOf(photoListItem)
//        assertThat(
//            photosPagingSource.load(
//                Refresh(
//                    key = 1,
//                    loadSize = 2,
//                    placeholdersEnabled = false
//                )
//            )
//        ).isEqualTo(
//            Page(
//                data = listOf(photoListItem.toDomain()),
//                prevKey = null,
//                nextKey = 2
//            )
//        )
//    }

//    @Test
//    fun loadReturnsOnNextNullOnEmptyData() = runBlocking {
//        every { apiResponse.data } returns emptyList()
// //        coEvery { mockPhotosRemoteSource.fetchPhotos(1) } returns apiResponse
//        assertThat(
//            photosPagingSource.load(
//                Refresh(
//                    key = 1,
//                    loadSize = 2,
//                    placeholdersEnabled = false
//                )
//            )
//        ).isEqualTo(
//            Page(
//                emptyList(),
//                prevKey = null,
//                nextKey = null
//            )
//        )
//    }
}
