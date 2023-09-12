package com.cmaina.presentation.screens.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.models.users.UserDomainModel
import com.cmaina.domain.repository.UsersRepository
import com.cmaina.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private val _uiState = MutableStateFlow(UserUiState(isLoading = true))
    val uiState: StateFlow<UserUiState> get() = _uiState

    fun fetchUser(username: String) = viewModelScope.launch {
        usersRepository.fetchUser(username = username).collect { user ->
            when (user) {
                is NetworkResult.Success -> {
                    val details = _uiState.value.uiDetails?.copy(
                        numberOfPhotosByUser = user.data.total_photos ?: 0,
                        userImageUrl = user.data.profile_image?.large ?: "",
                        followersCount = user.data.followers_count ?: 0,
                        followingCount = user.data.following_count ?: 0,
                        user = user.data
                    )
                    _uiState.value = _uiState.value.copy(uiDetails = details)
                }

                is NetworkResult.Error -> {
                }
            }
        }
    }

    fun fetchUserPhotos(username: String) = viewModelScope.launch {
        usersRepository.fetchUserPhotos(username = username).let {
            val details = _uiState.value.uiDetails?.copy(userPhotos = it)
            _uiState.value = _uiState.value.copy(uiDetails = details)
        }
    }
}
