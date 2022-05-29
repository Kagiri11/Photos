package com.cmaina.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.network.api.PhotosRemoteSource
import com.cmaina.repository.mappers.toDomain
import com.skydoves.sandwich.ApiResponse

class PhotosPagingSource(private val photosRemoteSource: PhotosRemoteSource) :
    PagingSource<Int, List<DomainPhotoListItem>>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, List<DomainPhotoListItem>> {
        val nextPageNumber = params.key ?: 1
        return when (val sourceResponse = photosRemoteSource.fetchPhotos(page = nextPageNumber)) {
            is ApiResponse.Success -> {
                val list = sourceResponse.data.toDomain()
                LoadResult.Page(
                    data = listOf(list),
                    prevKey = null,
                    nextKey = nextPageNumber + (params.loadSize / 9)
                )
            }
            is ApiResponse.Failure.Error -> {
                LoadResult.Error(throwable = Exception(message = "Failed to get load result from paging"))
            }
            is ApiResponse.Failure.Exception -> {
                LoadResult.Error(throwable = sourceResponse.exception)
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, List<DomainPhotoListItem>>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition=anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
