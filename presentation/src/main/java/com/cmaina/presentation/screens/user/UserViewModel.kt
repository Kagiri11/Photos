package com.cmaina.presentation.screens.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.domain.repository.UsersRepository
import com.cmaina.domain.utils.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val usersRepository: UsersRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserUiState(isLoading = true))
    val uiState: StateFlow<UserUiState> get() = _uiState

    fun fetchUser(username: String) = viewModelScope.launch {
        usersRepository.fetchUser(username = username).collect { networkResult ->
            when (networkResult) {
                is com.cmaina.domain.utils.NetworkResult.Result.Success -> {
                    with(networkResult.data) {
                        val details = UserUiDetails(
                            numberOfPhotosByUser = total_photos ?: 0,
                            userImageUrl = profile_image?.large ?: "",
                            followersCount = followers_count ?: 0,
                            followingCount = following_count ?: 0,
                            userPhotos = usersRepository.fetchUserPhotos(username),
                            user = this
                        )
                        _uiState.value = UserUiState(uiDetails = details, isLoading = false)
                    }
                }

                is com.cmaina.domain.utils.NetworkResult.Result.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = networkResult.errorDetails
                    )
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
