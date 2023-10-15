package com.cmaina.repository.mappers

import com.cmaina.domain.models.auth.AuthDomainResponse
import com.cmaina.domain.models.photos.DomainPhotoLinks
import com.cmaina.domain.models.photos.Photo
import com.cmaina.domain.models.photos.DomainPhotoUser
import com.cmaina.domain.models.photos.DomainProfileImage
import com.cmaina.domain.models.photos.DomainSponsor
import com.cmaina.domain.models.photos.DomainSponsorLinks
import com.cmaina.domain.models.photos.DomainSponsorSocial
import com.cmaina.domain.models.photos.PhotoUrls
import com.cmaina.domain.models.photos.DomainUserLinks
import com.cmaina.domain.models.photos.DomainUserProfileImage
import com.cmaina.domain.models.photos.DomainUserSocial
import com.cmaina.domain.models.photos.SponsorshipDomainModel
import com.cmaina.domain.models.photostats.DomainPhotoStatDownloads
import com.cmaina.domain.models.photostats.DomainPhotoStatLikes
import com.cmaina.domain.models.photostats.DomainPhotoStatistics
import com.cmaina.domain.models.photostats.DomainPhotoStatsViews
import com.cmaina.domain.models.search.PhotoSearchResultDomainModel
import com.cmaina.domain.models.specificphoto.CollectionDomainModel
import com.cmaina.domain.models.specificphoto.CoverPhotoDomainModel
import com.cmaina.domain.models.specificphoto.LocationDomainModel
import com.cmaina.domain.models.specificphoto.MetaDomainModel
import com.cmaina.domain.models.specificphoto.PositionDomainModel
import com.cmaina.domain.models.specificphoto.PreviewPhotoDomainModel
import com.cmaina.domain.models.specificphoto.RelatedCollectionsDomainModel
import com.cmaina.domain.models.specificphoto.ResultLinksDomainModel
import com.cmaina.domain.models.users.ProfileImageDomainModel
import com.cmaina.network.models.auth.AuthRemoteResponse
import com.cmaina.network.models.photos.PhotoLinks
import com.cmaina.network.models.photos.PhotoListItem
import com.cmaina.network.models.photos.ProfileImage
import com.cmaina.network.models.photos.Social
import com.cmaina.network.models.photos.Sponsor
import com.cmaina.network.models.photos.SponsorLinks
import com.cmaina.network.models.photos.Sponsorship
import com.cmaina.network.models.photos.Urls
import com.cmaina.network.models.photos.UserLinks
import com.cmaina.network.models.photos.UserProfileImage
import com.cmaina.network.models.photostats.Downloads
import com.cmaina.network.models.photostats.Likes
import com.cmaina.network.models.photostats.PhotoStatistics
import com.cmaina.network.models.photostats.Views
import com.cmaina.network.models.search.PhotoSearchResultDto
import com.cmaina.network.models.search.SearchedPhotoDto
import com.cmaina.network.models.specificphoto.CoverPhoto
import com.cmaina.network.models.specificphoto.Location
import com.cmaina.network.models.specificphoto.Meta
import com.cmaina.network.models.specificphoto.Position
import com.cmaina.network.models.specificphoto.PreviewPhoto
import com.cmaina.network.models.specificphoto.RelatedCollections
import com.cmaina.network.models.specificphoto.Result
import com.cmaina.network.models.specificphoto.ResultLinks
import com.cmaina.network.models.specificphoto.Source
import com.cmaina.network.models.specificphoto.SpecificPhoto
import com.cmaina.network.models.specificphoto.Topic
import com.cmaina.network.models.users.UserDto

internal fun PhotoListItem.toDomain() = Photo(
    blurHash = blur_hash ?: "",
    description = description ?: "",
    id = id,
    likedByUser = liked_by_user ?: false,
    likes = likes ?: 0,
    photoUrls = urls!!.toDomain()
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

internal fun com.cmaina.network.models.photos.User.toDomain() = DomainPhotoUser(
    acceptedTos = accepted_tos, bio = bio, firstName = first_name,
    forHire = for_hire,
    id = id,
    instagramUsername = instagram_username,
    lastName = last_name,
    domainUserLinks = userLinks?.toDomian(),
    location = location, name = name,
    portfolioUrl = portfolio_url,
    domainUserProfileImage = userProfileImage?.toDomain(),
    domainUserSocial = social?.toDomain(),
    totalCollections = total_collections,
    totalLikes = total_likes,
    totalPhotos = total_photos,
    twitterUsername = twitter_username,
    updatedAt = updated_at,
    username = username
)

internal fun UserLinks.toDomian() = DomainUserLinks(
    followers = followers,
    following = following,
    html = html,
    likes = likes,
    photos = photos,
    portfolio = portfolio,
    self = self
)

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

internal fun PhotoLinks.toDomain() = DomainPhotoLinks(
    download = download,
    downloadLocation = download_location,
    html = html,
    self = self
)

internal fun ProfileImage.toDomain() = DomainProfileImage(
    large = large,
    medium = medium,
    small = small
)

// user section
internal fun UserDto.toDomain() = com.cmaina.domain.models.users.User(
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
internal fun Location.toDomain() = LocationDomainModel(
    city = city,
    country = country,
    name = name,
    positionDomainModel = position?.toDomain(),
    title = title
)

internal fun Position.toDomain() = PositionDomainModel(latitude, longitude)
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
    photoUrls = urls.toDomain()
)

internal fun AuthRemoteResponse.toDomain() = AuthDomainResponse(
    accessToken = accessToken,
    createdAt = createdAt,
    scope = scope,
    tokenType = tokenType,
    refreshToken = refreshToken
)
