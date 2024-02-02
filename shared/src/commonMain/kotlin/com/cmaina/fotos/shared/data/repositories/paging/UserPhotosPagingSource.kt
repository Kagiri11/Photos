package com.cmaina.fotos.shared.data.repositories.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cmaina.fotos.shared.data.mappers.toDomain
import com.cmaina.fotos.shared.data.network.InOut
import com.cmaina.fotos.shared.data.network.UsersRemoteSource
import com.cmaina.fotos.shared.data.network.models.photos.PhotoListItem
import com.cmaina.fotos.shared.domain.models.photos.Photo
import com.cmaina.fotos.shared.domain.utils.Result
import io.ktor.client.call.body

class UserPhotosPagingSource(
    private val usersRemoteSource: UsersRemoteSource,
    val username: String
) :
    PagingSource<Int, Photo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val nextPageNumber = params.key ?: 1

        val response = usersRemoteSource.getUserPhotos(username = username, page = nextPageNumber)
        val result = InOut<List<PhotoListItem>, List<Photo>>(response.body())
            .apiCall(response){it.map { it.toDomain() }}
        return when (
            result
        ) {
            is Result.Success -> {
                val dataResponse = result.data
                LoadResult.Page(
                    data = dataResponse,
                    prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                    nextKey = if (dataResponse.isEmpty()) null else nextPageNumber + 1
                )
            }

            is Result.Error -> {
                LoadResult.Error(throwable = Throwable())
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
