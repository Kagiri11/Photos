package com.cmaina.tacc.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.domain.PhotosRepository
import com.cmaina.domain.models.DomainPhoto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListViewModel(private val photosRepository: PhotosRepository) : ViewModel() {

    private val _photosOfMars = MutableStateFlow<List<DomainPhoto>>(emptyList())
    val photosOfMars: StateFlow<List<DomainPhoto>> get() = _photosOfMars

    private val _photoDetail = MutableStateFlow<DomainPhoto?>(null)
    val photoDetail: StateFlow<DomainPhoto?> get() = _photoDetail

    init {
        fetchPhotos()
    }

    private fun fetchPhotos() {
        viewModelScope.launch {
            photosRepository.fetchMarsPhotos().collect {
                _photosOfMars.value = it
            }
        }
    }

    fun setPhotoToBeViewed(domainPhoto: DomainPhoto) = viewModelScope.launch {
        _photoDetail.value = domainPhoto
    }
}
