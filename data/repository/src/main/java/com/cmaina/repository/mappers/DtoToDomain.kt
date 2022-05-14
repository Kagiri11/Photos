package com.cmaina.repository.mappers

import com.cmaina.domain.models.photos.DomainPhotoLinks
import com.cmaina.domain.models.photos.DomainPhotoList
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.models.photos.DomainPhotoUser
import com.cmaina.domain.models.photos.DomainProfileImage
import com.cmaina.domain.models.photos.DomainSponsor
import com.cmaina.domain.models.photos.DomainSponsorLinks
import com.cmaina.domain.models.photos.DomainSponsorSocial
import com.cmaina.domain.models.photos.DomainTopicSubmissions
import com.cmaina.domain.models.photos.DomainUrls
import com.cmaina.domain.models.photos.DomainUserLinks
import com.cmaina.domain.models.photos.DomainUserProfileImage
import com.cmaina.domain.models.photos.DomainUserSocial
import com.cmaina.domain.models.photos.SponsorshipDomainModel
import com.cmaina.domain.models.specificphoto.MetaDomainModel
import com.cmaina.domain.models.users.UserDomainModel
import com.cmaina.domain.models.users.UserPhotoDomainModel
import com.cmaina.network.models.photos.PhotoLinks
import com.cmaina.network.models.photos.PhotoList
import com.cmaina.network.models.photos.PhotoListItem
import com.cmaina.network.models.photos.ProfileImage
import com.cmaina.network.models.photos.Social
import com.cmaina.network.models.photos.Sponsor
import com.cmaina.network.models.photos.SponsorLinks
import com.cmaina.network.models.photos.Sponsorship
import com.cmaina.network.models.photos.Urls
import com.cmaina.network.models.photos.User
import com.cmaina.network.models.photos.UserLinks
import com.cmaina.network.models.photos.UserProfileImage
import com.cmaina.network.models.search.TopicSubmissions
import com.cmaina.network.models.specificphoto.Meta
import com.cmaina.network.models.users.Photo
import com.cmaina.network.models.users.UserDto

internal fun PhotoList.toDomain() = DomainPhotoList(
    domainPhotoList = photoList.map { it.toDomain() }
)

internal fun PhotoListItem.toDomain() = DomainPhotoListItem(
    altDescription = alt_description,
    blurHash = blur_hash,
    categories = categories,
    color = color,
    created_at = created_at,
    currentUserCollections = current_user_collections,
    description = description,
    height = height,
    id = id,
    likedByUser = liked_by_user,
    likes = likes,
    linksDomain = links.toDomain(),
    promotedAt = promoted_at,
    sponsorshipDomainModel = sponsorship.toDomain(),
    domainTopicSubmissions = topic_submissions.toDomain(),
    updatedAt = updated_at,
    domainUrls = urls.toDomain(),
    domainPhotoUser = user.toDomain(),
    width = width
)

internal fun User.toDomain() = DomainPhotoUser(
    acceptedTos = accepted_tos, bio = bio, firstName = first_name,
    forHire = for_hire,
    id = id,
    instagramUsername = instagram_username,
    lastName = last_name,
    domainUserLinks = userLinks.toDomian(),
    location = location, name = name,
    portfolioUrl = portfolio_url,
    domainUserProfileImage = userProfile_image.toDomain(),
    domainUserSocial = social.toDomain(),
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

internal fun Urls.toDomain() = DomainUrls(
    full = full,
    raw = raw,
    regular = regular,
    small = small,
    smallS3 = small_s3,
    thumb = thumb
)

internal fun TopicSubmissions.toDomain() = DomainTopicSubmissions()

internal fun PhotoLinks.toDomain() = DomainPhotoLinks(
    download = download,
    downloadLocation = download_location,
    html = html,
    self = self
)

internal fun Sponsor.toDomain() = DomainSponsor(
    acceptedTos = accepted_tos,
    bio = bio,
    firstName = first_name,
    forHire = for_hire,
    id = id,
    instagramUsername = instagram_username,
    lastName = last_name,
    domainSponsorLinks = sponsorLinks.toDomain(),
    location = location,
    name = name,
    portfolioUrl = portfolio_url,
    domainProfileImage = profile_image.toDomain(),
    domainSponsorSocial = sponsorSocial.toDomainSponsorSocial(),
    totalCollections = total_collections,
    totalLikes = total_likes,
    totalPhotos = total_photos,
    twitterUsername = twitter_username,
    updatedAt = updated_at,
    userName = username
)

internal fun Social.toDomainSponsorSocial() = DomainSponsorSocial(
    instagram_username,
    paypalEmail = paypal_email,
    portfolioUrl = portfolio_url,
    twitterUsername = twitter_username

)

internal fun ProfileImage.toDomain() = DomainProfileImage(
    large = large,
    medium = medium,
    small = small
)

internal fun SponsorLinks.toDomain() = DomainSponsorLinks(
    followers = followers,
    following = following,
    html = html,
    likes = likes,
    photos = photos,
    portfolio = portfolio,
    self = self
)

internal fun Sponsorship.toDomain() = SponsorshipDomainModel(
    tagline = tagline,
    taglineUrl = tagline_url,
    impressionUrls = impression_urls,
    domainSponsor = sponsor.toDomain()
)

// user section
internal fun UserDto.toDomain() = UserDomainModel(
    accepted_tos = accepted_tos,
    allow_messages = allow_messages,
    badge = badge,
    bio = bio,
    downloads = downloads,
    first_name = first_name,
    followed_by_user = followed_by_user,
    followers_count = followers_count,
    following_count = following_count,
    for_hire = for_hire,
    id = id,
    instagram_username = instagram_username,
    last_name = last_name,
    links = links.toDomain(),
    location = location,
    metaDomainModel = meta.toDomain(),
    name = name,
    numeric_id = numeric_id,
    userPhotoDomainModels = photos.map { it.toDomain() },
    portfolio_url = portfolio_url,
    profile_image = profile_image,
    social = social.toDomain(),
    tags, total_collections, total_likes, total_photos, twitter_username, updated_at, username
)

internal fun Meta.toDomain() = MetaDomainModel(index = index)

internal fun Photo.toDomain() = UserPhotoDomainModel(
    blur_hash = blur_hash,
    id = id,
    created_at = created_at,
    updated_at = updated_at,
    urls = urls.toDomain()
)
