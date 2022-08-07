package com.cmaina.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.network.api.PhotosRemoteSource
import com.cmaina.repository.mappers.toDomain
import com.skydoves.sandwich.ApiResponse

class SearchedPhotosPagingSource(
    private val photosRemoteSource: PhotosRemoteSource,
    private val searchString: String
) : PagingSource<Int, DomainPhotoListItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DomainPhotoListItem> {
        val nextPageNumber = params.key ?: 1
        return when (
            val sourceResponse =
                photosRemoteSource.searchPhotos(searchQuery = searchString, page = nextPageNumber)
        ) {
            is ApiResponse.Success -> {
                val dataResponse = sourceResponse.data.results.map { it.toDomain() }
                LoadResult.Page(
                    data = dataResponse,
                    prevKey = null,
                    nextKey = nextPageNumber + (params.loadSize / 10)
                )
            }
            is ApiResponse.Failure.Error -> {
                LoadResult.Error(throwable = Throwable())
            }
            is ApiResponse.Failure.Exception -> {
                LoadResult.Error(throwable = sourceResponse.exception)
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DomainPhotoListItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition = anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
