package com.cmaina.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cmaina.domain.models.photos.Photo
import com.cmaina.domain.models.search.PhotoSearchResultDomainModel
import com.cmaina.domain.utils.Result
import com.cmaina.network.api.PhotosRemoteSource
import com.cmaina.network.models.search.PhotoSearchResultDto
import com.cmaina.repository.mappers.toDomain
import com.cmaina.repository.utils.InOut
import io.ktor.client.call.body

class SearchedPhotosPagingSource(
    private val photosRemoteSource: PhotosRemoteSource,
    private val searchString: String
) : PagingSource<Int, Photo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val nextPageNumber = params.key ?: 1
        val call = photosRemoteSource.searchPhotos(
            searchQuery = searchString,
            page = nextPageNumber
        )
        val result = InOut<PhotoSearchResultDto, PhotoSearchResultDomainModel>(call.body())
            .apiCall(response = call) { it.toDomain() }

        return when (
            result
        ) {
            is Result.Success -> {
                val dataResponse = result.data.searchedPhotoDomainModels
                LoadResult.Page(
                    data = dataResponse ?: emptyList(),
                    prevKey = null,
                    nextKey = nextPageNumber + (params.loadSize / 10)
                )
            }

            is Result.Error -> {
                LoadResult.Error(throwable = Throwable(result.errorDetails))
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition = anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
