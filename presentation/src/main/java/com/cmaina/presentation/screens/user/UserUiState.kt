package com.cmaina.presentation.screens.user

import androidx.paging.PagingData
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.models.users.UserDomainModel
import kotlinx.coroutines.flow.Flow

sealed interface UserUiState {

    data class Success(val uiDetails: UserUiDetails) : UserUiState

    object Loading : UserUiState

    data class Error(val errorMessage: String) : UserUiState
}

data class UserUiDetails(
    val userPhotos: Flow<PagingData<DomainPhotoListItem>>,
    val userImageUrl: String,
    val numberOfPhotosByUser: Int,
    val followersCount: Int,
    val followingCount: Int,
    val user: UserDomainModel
)

