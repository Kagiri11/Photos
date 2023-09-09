package com.cmaina.presentation.screens.home

import androidx.paging.PagingData
import com.cmaina.domain.models.photos.DomainPhotoListItem
import kotlinx.coroutines.flow.Flow

data class HomeUiState(
    val pagedPhotos: Flow<PagingData<DomainPhotoListItem>>? = null,
    val isLoading: Boolean,
    val errorMessage: String? = null
)
