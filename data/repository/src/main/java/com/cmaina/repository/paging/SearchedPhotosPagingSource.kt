package com.cmaina.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.utils.Result
import com.cmaina.network.api.PhotosNetworkSource
import com.cmaina.repository.mappers.toDomain
import com.cmaina.repository.utils.safeApiCall

class SearchedPhotosPagingSource(
    private val photosNetworkSource: PhotosNetworkSource,
    private val searchString: String
) : PagingSource<Int, DomainPhotoListItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DomainPhotoListItem> {
        val nextPageNumber = params.key ?: 1
        val sourceResponse = safeApiCall {
            photosNetworkSource.searchPhotos(
                searchQuery = searchString,
                page = nextPageNumber
            )
        }

        return when (
            sourceResponse
        ) {
            is com.cmaina.domain.utils.NetworkResult.Result.Success -> {
                val dataResponse = sourceResponse.data.results.map { it.toDomain() }
                LoadResult.Page(
                    data = dataResponse,
                    prevKey = null,
                    nextKey = nextPageNumber + (params.loadSize / 10)
                )
            }
            is com.cmaina.domain.utils.NetworkResult.Result.Error -> {
                LoadResult.Error(throwable = Throwable(sourceResponse.errorDetails))
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
