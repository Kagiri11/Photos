package com.cmaina.network.api

import com.cmaina.network.models.photos.PhotoListItem
import com.cmaina.network.models.photostats.PhotoStatistics
import com.cmaina.network.models.search.PhotoSearchResultDto
import com.cmaina.network.models.specificphoto.SpecificPhoto
import com.cmaina.network.utils.Constants.BASEURL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get


class PhotosRemoteSource(private val client: HttpClient) {

    suspend fun fetchPhotos(page: Int ) = client.get(urlString = "${BASEURL}photos") {
        url {
            parameters.append("page", "$page")
        }
    }

    suspend fun fetchPhoto(id: String) = client.get("${BASEURL}photos/$id")

    suspend fun fetchRandomPhoto() = client.get("${BASEURL}photos/random")

    suspend fun likePhoto(id: String) = client.get("photos/$id/like")

    suspend fun fetchPhotoStatistics(id: String) =
        client.get("${BASEURL}photos/$id/statistics")

    suspend fun searchPhotos(
        searchQuery: String,
        page: Int
    ) = client.get("${BASEURL}search/photos") {
        url {
            parameters.append("query", searchQuery)
            parameters.append("page", page.toString())
        }
    }

}