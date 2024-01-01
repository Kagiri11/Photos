package com.cmaina.repository.mappers

import com.cmaina.domain.models.auth.AuthDomainResponse
import com.cmaina.domain.models.photos.Photo
import com.cmaina.domain.models.photos.DomainProfileImage
import com.cmaina.domain.models.photos.PhotoUrls
import com.cmaina.domain.models.photos.DomainUserProfileImage
import com.cmaina.domain.models.photos.DomainUserSocial
import com.cmaina.domain.models.photos.PhotoUser
import com.cmaina.domain.models.photostats.DomainPhotoStatDownloads
import com.cmaina.domain.models.photostats.DomainPhotoStatLikes
import com.cmaina.domain.models.photostats.DomainPhotoStatistics
import com.cmaina.domain.models.photostats.DomainPhotoStatsViews
import com.cmaina.domain.models.search.PhotoSearchResultDomainModel
import com.cmaina.domain.models.users.ProfileImageDomainModel
import com.cmaina.domain.models.users.User
import com.cmaina.network.models.auth.AuthRemoteResponse
import com.cmaina.network.models.photos.PhotoListItem
import com.cmaina.network.models.photos.ProfileImage
import com.cmaina.network.models.photos.Social
import com.cmaina.network.models.photos.Urls
import com.cmaina.network.models.photos.UserProfileImage
import com.cmaina.network.models.photostats.Downloads
import com.cmaina.network.models.photostats.Likes
import com.cmaina.network.models.photostats.PhotoStatistics
import com.cmaina.network.models.photostats.Views
import com.cmaina.network.models.search.PhotoSearchResultDto
import com.cmaina.network.models.search.SearchedPhotoDto
import com.cmaina.network.models.specificphoto.Location
import com.cmaina.network.models.specificphoto.Position
import com.cmaina.network.models.users.UserDto

internal fun PhotoListItem.toDomain() = Photo(
    blurHash = blur_hash ?: "",
    description = description ?: "",
    id = id,
    likedByUser = liked_by_user ?: false,
    likes = likes ?: 0,
    photoUrls = urls!!.toDomain(),
    user = user?.toDomain()!!,
    relatedPhotos = this.collections.results.map {
        mapOf(it.id to it.cover_photo.urls.full)
    }
)

internal fun com.cmaina.network.models.photos.User.toDomain() = PhotoUser(
    userName = username ?: "",
    userPhotoImageUrl = this.userProfileImage.medium

)

internal fun PhotoStatistics.toDomain() = DomainPhotoStatistics(
    id = id,
    domainPhotoStatLikes = likes.toDomain(),
    domainPhotoStatDownloads = downloads.toDomain(),
    domainPhotoStatsViews = views.toDomain()
)

internal fun Likes.toDomain() = DomainPhotoStatLikes(
    total = total
)

internal fun Downloads.toDomain() =
    DomainPhotoStatDownloads(total = total)

internal fun Views.toDomain() =
    DomainPhotoStatsViews(total = total)

internal fun UserProfileImage.toDomain() = DomainUserProfileImage(
    large, medium, small
)

internal fun Social.toDomain() = DomainUserSocial(
    instagramUsername = instagram_username,
    paypalEmail = paypal_email,
    portfolioUrl = portfolio_url,
    twitterUsername = twitter_username
)

internal fun Urls.toDomain() = PhotoUrls(
    full = full,
    raw = raw,
    regular = regular,
    small = small,
    smallS3 = small_s3,
    thumb = thumb
)

internal fun ProfileImage.toDomain() = DomainProfileImage(
    large = large,
    medium = medium,
    small = small
)

// user section
internal fun UserDto.toDomain() = User(
    bio = bio,
    downloads = downloads,
    firstName = first_name,
    followedByUser = followed_by_user,
    followersCount = followers_count,
    followingCount = following_count,
    forHire = for_hire,
    id = id,
    instagramUsername = instagram_username,
    lastName = last_name as String,
    name = name,
    userPhotos = photos.map { it.toDomain() },
    profileImage = profile_image.toDomain(),
    social = social.toDomain(),
    totalLikes = total_likes,
    totalPhotos = total_photos,
    username = username
)

internal fun com.cmaina.network.models.users.ProfileImage.toDomain() =
    ProfileImageDomainModel(large, medium, small)

internal fun PhotoSearchResultDto.toDomain() = PhotoSearchResultDomainModel(
    searchedPhotoDomainModels = results.map { it.toDomain() },
    total = total,
    totalPages = total_pages
)

internal fun SearchedPhotoDto.toDomain() = Photo(
    blurHash = blur_hash,
    description = description,
    id = id,
    likedByUser = liked_by_user,
    likes = likes,
    photoUrls = urls.toDomain(),
    user = this.user.toDomain(),
    relatedPhotos = this.collections.results.map {
        mapOf(it.id to it.cover_photo.urls.full)
    }
)

internal fun AuthRemoteResponse.toDomain() = AuthDomainResponse(
    accessToken = accessToken,
    createdAt = createdAt,
    scope = scope,
    tokenType = tokenType,
    refreshToken = refreshToken
)
