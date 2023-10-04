package com.cmaina.presentation.screens.home

import androidx.paging.PagingData
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.presentation.screens.photodetails.Details
import kotlinx.coroutines.flow.Flow

data class HomeState(
    val pagedPhotos: Flow<PagingData<DomainPhotoListItem>>? = null,
    val isLoading: Boolean,
    val errorMessage: String? = null
)
sealed interface HomeUiState{

    data class Success(val pagedPhotos: Flow<PagingData<DomainPhotoListItem>>): HomeUiState

    object Loading: HomeUiState

    data class Error(val errorMessage: String): HomeUiState
}

