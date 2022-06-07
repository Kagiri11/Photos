package com.cmaina.repository.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.network.api.PhotosRemoteSource
import com.cmaina.repository.mappers.toDomain
import com.skydoves.sandwich.ApiResponse

class PhotosPagingSource(private val photosRemoteSource: PhotosRemoteSource) :
    PagingSource<Int, List<DomainPhotoListItem>>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, List<DomainPhotoListItem>> {
        Log.d("DomainPhotos", "This has been called")
        val nextPageNumber = params.key ?: 1
        return when (val sourceResponse = photosRemoteSource.fetchPhotos(page = nextPageNumber)) {
            is ApiResponse.Success -> {
                val list = sourceResponse.data.toDomain()
                Log.d("DomainPhotos", "This is the list: ${list.size}")
                LoadResult.Page(
                    data = listOf(list),
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

    override fun getRefreshKey(state: PagingState<Int, List<DomainPhotoListItem>>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition = anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
