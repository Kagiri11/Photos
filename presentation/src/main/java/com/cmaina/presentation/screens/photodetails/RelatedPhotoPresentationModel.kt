package com.cmaina.presentation.screens.photodetails

import com.cmaina.domain.models.photos.DomainPhotoUser
import com.cmaina.domain.models.specificphoto.PreviewPhotoDomainModel

data class RelatedPhotoPresentationModel(
    val previewPhotoDomainModel: PreviewPhotoDomainModel,
    val userDomainModel: DomainPhotoUser
)
