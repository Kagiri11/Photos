package com.cmaina.repository.paging

import com.cmaina.network.api.PhotosRemoteSource
import com.cmaina.network.models.photos.PhotoListItem
import io.mockk.mockk
import org.junit.Before

class SearchedPhotosPagingSourceTest {

    // System under test
    private lateinit var searchedPhotosPagingSource: SearchedPhotosPagingSource

    // Helpers
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

    @Before
    fun setup() {
        val photosRemoteSource = mockk<PhotosRemoteSource>()
        searchedPhotosPagingSource = SearchedPhotosPagingSource(photosRemoteSource, "nikita")
    }
}
