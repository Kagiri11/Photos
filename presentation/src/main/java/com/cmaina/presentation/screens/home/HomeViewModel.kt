package com.cmaina.presentation.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.repository.PhotosRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class HomeViewModel(
    private val photosRepository: PhotosRepository
) : ViewModel() {
    init {
        searchPhotos()
    }

    private val _pics = MutableLiveData<Flow<PagingData<DomainPhotoListItem>>>(null)
    val pics: LiveData<Flow<PagingData<DomainPhotoListItem>>> get() = _pics
    val searchString = MutableStateFlow("")

    @OptIn(FlowPreview::class)
    fun searchPhotos() {
        viewModelScope.launch {
            delay(100)
            searchString
                .debounce(1000)
                .distinctUntilChanged()
                .collectLatest {
                    if (it.isNotEmpty()) {
                        photosRepository.searchPhoto(searchString = it).let { result ->
                            delay(200)
                            _pics.value = result
                        }
                    } else {
                        returnNullPhotos()
                    }
                }
        }
    }

    fun fetchPhotos() {
        viewModelScope.launch {
            photosRepository.fetchPhotos().let {
                _pics.value = it
            }
        }
    }

    private fun returnNullPhotos() {
        viewModelScope.launch {
            _pics.value = null
            fetchPhotos()
        }
    }
}
