package com.cmaina.repository.mappers

import com.cmaina.domain.models.photos.DomainProfileImage
import com.cmaina.domain.models.photos.DomainUrls
import com.cmaina.domain.models.photos.DomainUserProfileImage
import com.cmaina.domain.models.photostats.DomainPhotoStatDownloads
import com.cmaina.domain.models.photostats.DomainPhotoStatLikes
import com.cmaina.domain.models.photostats.DomainPhotoStatistics
import com.cmaina.domain.models.photostats.DomainPhotoStatsViews
import com.cmaina.domain.models.specificphoto.LocationDomainModel
import com.cmaina.network.models.photos.ProfileImage
import com.cmaina.network.models.photos.Urls
import com.cmaina.network.models.photos.UserProfileImage
import com.cmaina.network.models.photostats.Downloads
import com.cmaina.network.models.photostats.Likes
import com.cmaina.network.models.photostats.PhotoStatistics
import com.cmaina.network.models.photostats.Views
import com.cmaina.network.models.specificphoto.Location
import com.cmaina.repository.utils.DomainPhotoListItem
import com.cmaina.repository.utils.PhotoListItem
import com.cmaina.repository.utils.historical
import com.cmaina.repository.utils.id
import com.cmaina.repository.utils.total
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class DtoToDomainTest {

    @Test
    fun `confirm that PhotoListItem toDomain results in a desired DomainPhotoListItem`() {
        assertThat(PhotoListItem.toDomain()).isEqualTo(DomainPhotoListItem)
    }

    @Test
    fun `confirm PhotoStatistics toDomain results in a desired DomainPhotoStatistics`() {
        val photoStatistics = PhotoStatistics(
            id = id,
            views = Views(historical, total),
            downloads = Downloads(historical, total),
            likes = Likes(historical, total)
        )
        val domainPhotoStatistics = DomainPhotoStatistics(
            domainPhotoStatDownloads = DomainPhotoStatDownloads(total),
            id = id,
            domainPhotoStatLikes = DomainPhotoStatLikes(total),
            domainPhotoStatsViews = DomainPhotoStatsViews(total)
        )
        assertThat(photoStatistics.toDomain()).isEqualTo(domainPhotoStatistics)
    }

    @Test
    fun `confirm UserProfileImage toDomain results in a desired DomainUserProfileImage`() {
        val userProfileImage = UserProfileImage(large = "large", medium = "medium", small = "small")
        val domainUserProfileImage =
            DomainUserProfileImage(large = "large", medium = "medium", small = "small")
        assertThat(userProfileImage.toDomain()).isEqualTo(domainUserProfileImage)
    }

    @Test
    fun `confirm Urls toDomain results in desired DomainUrl`() {
        val urls = Urls(
            full = "full",
            raw = "raw",
            regular = "regular",
            small = "small",
            small_s3 = "small",
            thumb = null
        )
        val domainUrls = DomainUrls(
            full = "full",
            raw = "raw",
            regular = "regular",
            small = "small",
            thumb = null,
            smallS3 = "small"
        )
        assertThat(urls.toDomain()).isEqualTo(domainUrls)
    }

    @Test
    fun `confirm ProfileImage toDomain results in a DomainProfileImage`() {
        val profileImage = ProfileImage(large = "lager", medium = "medium", small = "smaller")
        val domainProfileImage = DomainProfileImage(large = "lager", medium = "medium", small = "smaller")
        assertThat(profileImage.toDomain()).isEqualTo(domainProfileImage)
    }

    @Test
    fun `confirm Location toDomain results in LocationDomainModel`(){
        val location = Location(city = "Nairobi", country = "Kenya", name = "Kenya", position = null, title = "Nairobi ,Kenya")
        val locationDomainModel = LocationDomainModel(city = "Nairobi", country = "Kenya", name = "Kenya", title = "Nairobi ,Kenya", positionDomainModel = null)
        assertThat(location.toDomain()).isEqualTo(locationDomainModel)
    }
}
