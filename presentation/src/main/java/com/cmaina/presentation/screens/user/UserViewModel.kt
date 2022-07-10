package com.cmaina.presentation.screens.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.models.users.UserDomainModel
import com.cmaina.domain.usecases.FetchUserPhotosUseCase
import com.cmaina.domain.usecases.FetchUserUseCase
import kotlinx.coroutines.launch

class UserViewModel(
    private val fetchUserUseCase: FetchUserUseCase,
    private val fetchUserPhotosUseCase: FetchUserPhotosUseCase
) : ViewModel() {

    private val _user = MutableLiveData<UserDomainModel>()
    val user: LiveData<UserDomainModel> get() = _user

    private val _userPhotos = MutableLiveData<List<DomainPhotoListItem>>()
    val userPhotos: LiveData<List<DomainPhotoListItem>> get() = _userPhotos

    fun fetchUser(username: String) = viewModelScope.launch {
        fetchUserUseCase(username = username).collect {
            _user.value = it
        }
    }

    fun fetchUserPhotos(username: String) = viewModelScope.launch {
        fetchUserPhotosUseCase(username = username).collect {
            _userPhotos.value = it
        }
    }
}
