package com.cmaina.fotos.shared.presentation.screens.user


import com.cmaina.fotos.shared.domain.repositories.PhotosRepository
import com.cmaina.fotos.shared.domain.repositories.UsersRepository
import com.cmaina.fotos.shared.domain.utils.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.cmaina.fotos.shared.presentation.utils.ViewModel
import kotlinx.coroutines.launch

class UserViewModel(
    private val usersRepository: UsersRepository,
    private val photosRepository: PhotosRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UserUiState>(UserUiState.Loading)
    val uiState: StateFlow<UserUiState> get() = _uiState

    fun fetchUser(username: String) = viewModelScope.launch {
        usersRepository.fetchUser(username = username).collect { networkResult ->
            when (networkResult) {
                is Result.Success -> {
                    with(networkResult.data) {
                        val details = UserUiDetails(
                            numberOfPhotosByUser = totalPhotos,
                            userImageUrl = profileImage.large ?: "",
                            followersCount = followersCount,
                            followingCount = followingCount,
                            userPhotos = photosRepository.fetchUserPhotos(username),
                            userName = this.name
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
