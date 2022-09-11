package com.cmaina.presentation.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.repository.PhotosRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class HomeViewModel(
    private val photosRepository: PhotosRepository
) : ViewModel() {

    private val _pics = MutableLiveData<Flow<PagingData<DomainPhotoListItem>>>(flowOf())
    val pics: LiveData<Flow<PagingData<DomainPhotoListItem>>> get() = _pics

    init {
        fetchPhotos()
    }

    private fun fetchPhotos() {
        viewModelScope.launch {
            photosRepository.fetchPhotos().let { pagingDataFlow ->
                _pics.value = pagingDataFlow
            }
        }
    }
}
