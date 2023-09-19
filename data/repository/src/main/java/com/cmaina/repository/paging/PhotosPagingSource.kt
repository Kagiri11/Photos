package com.cmaina.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.utils.Result
import com.cmaina.network.api.PhotosNetworkSource
import com.cmaina.network.api.PhotosRemoteSource
import com.cmaina.network.models.photos.PhotoListItem
import com.cmaina.repository.mappers.toDomain
import com.cmaina.repository.utils.InOut
import com.cmaina.repository.utils.safeApiCall
import io.ktor.client.call.body

class PhotosPagingSource(private val photosRemoteSource: PhotosRemoteSource) :
    PagingSource<Int, DomainPhotoListItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DomainPhotoListItem> {
        val nextPageNumber = params.key ?: 1
        val call = photosRemoteSource.fetchPhotos(page = nextPageNumber)
        val result = InOut<List<PhotoListItem>, List<DomainPhotoListItem>>(call.body())
            .apiCall(call) { it.map { it.toDomain() } }
        return when (result) {
            is Result.Success -> {
                val dataResponse = result.data
                LoadResult.Page(
                    data = dataResponse,
                    prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                    nextKey = if (dataResponse.isEmpty()) null else nextPageNumber + 1
                )
            }

            is Result.Error -> {
                LoadResult.Error(Throwable(message = result.errorDetails))
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
