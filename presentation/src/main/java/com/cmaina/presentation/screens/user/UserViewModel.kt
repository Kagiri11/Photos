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

    private val _uiState = MutableStateFlow<UserUiState>(UserUiState.Loading)
    val uiState: StateFlow<UserUiState> get() = _uiState

    fun fetchUser(username: String) = viewModelScope.launch {
        usersRepository.fetchUser(username = username).collect { networkResult ->
            when (networkResult) {
                is Result.Success -> {
                    with(networkResult.data) {
                        val details = UserUiDetails(
                            numberOfPhotosByUser = total_photos ?: 0,
                            userImageUrl = profile_image?.large ?: "",
                            followersCount = followers_count ?: 0,
                            followingCount = following_count ?: 0,
                            userPhotos = usersRepository.fetchUserPhotos(username),
                            user = this
                        )
                        _uiState.value = UserUiState.Success(uiDetails = details)
                    }
                }

                is Result.Error -> {
                    _uiState.value = UserUiState.Error(errorMessage = networkResult.errorDetails)
                }
            }
        }
    }
}
