package com.cmaina.fotos

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.domain.usecases.FetchPhotosUseCase
import kotlinx.coroutines.launch

class HomeViewModel(private val fetchPhotosUseCase: FetchPhotosUseCase) : ViewModel() {

    init {
        fetchPhotos()
    }

    val numberOfPics: MutableState<Int> = mutableStateOf(0)

    private fun fetchPhotos() = viewModelScope.launch {
        fetchPhotosUseCase().collect {
            Log.d("PhotosCollected", "This is the data collected: ${it.size}")
            numberOfPics.value = it.size
        }
    }
}
