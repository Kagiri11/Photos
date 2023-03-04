package com.cmaina.tacc.screens.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.domain.PhotosRepository
import com.cmaina.domain.models.DomainPhoto
import com.cmaina.domain.utils.NetworkResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListViewModel(private val photosRepository: PhotosRepository) : ViewModel() {

    private val _marsPhotos: MutableLiveData<List<DomainPhoto>> = MutableLiveData()
    val marsPhotos: LiveData<List<DomainPhoto>> get() = _marsPhotos

    fun fetchPhotos() {
        viewModelScope.launch {
            photosRepository.fetchMarsPhotos().collect {
                when (it) {
                    is NetworkResult.Success -> {
                        _marsPhotos.value = it.data
                    }
                    is NetworkResult.Error -> {
                    }
                }
            }
        }
    }
}
