package com.cmaina.domain.repository

import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.models.users.UserDomainModel
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    suspend fun fetchUserProfile(): Flow<UserDomainModel>

    suspend fun fetchUserPhotos(): Flow<DomainPhotoListItem>
}
