package com.cmaina.presentation.screens.user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.models.users.UserDomainModel
import com.cmaina.domain.repository.UsersRepository
import com.cmaina.domain.utils.NetworkResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val usersRepository: UsersRepository
) : ViewModel() {

    private val _user = MutableLiveData<UserDomainModel>()
    val user: LiveData<UserDomainModel> get() = _user

    private val _userPhotos = MutableLiveData<Flow<PagingData<DomainPhotoListItem>>>()
    val userPhotos: LiveData<Flow<PagingData<DomainPhotoListItem>>> get() = _userPhotos

    private val _userImageUrl = MutableStateFlow("")
    val userImageUrl = _userImageUrl.asStateFlow()

    private val _usersPhotoCount = MutableStateFlow(0)
    val usersPhotoCount = _usersPhotoCount.asStateFlow()

    private val _usersFollowersCount = MutableStateFlow(0)
    val usersFollowersCount = _usersFollowersCount.asStateFlow()

    private val _usersFollowingCount = MutableStateFlow(0)
    val usersFollowingCount = _usersFollowingCount.asStateFlow()

    fun fetchUser(username: String) = viewModelScope.launch {
        usersRepository.fetchUser(username = username).collect { user ->
            when (user) {
                is NetworkResult.Success -> {
                    _user.value = user.data
                    _usersPhotoCount.value = user.data.total_photos ?: 0
                    _usersFollowersCount.value = user.data.followers_count ?: 0
                    _usersFollowingCount.value = user.data.followers_count ?: 0
                    _userImageUrl.value = user.data.profile_image?.large ?: ""
                }
                is NetworkResult.Error -> {
                }
            }
        }
    }

    fun fetchUserPhotos(username: String) = viewModelScope.launch {
        usersRepository.fetchUserPhotos(username = username).let {
            delay(200)
            _userPhotos.value = it
        }
    }
}
