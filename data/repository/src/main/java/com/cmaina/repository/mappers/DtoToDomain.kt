package com.cmaina.repository.mappers

import com.cmaina.domain.models.auth.AuthDomainResponse
import com.cmaina.domain.models.photos.DomainPhotoLinks
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.models.photos.DomainPhotoUser
import com.cmaina.domain.models.photos.DomainProfileImage
import com.cmaina.domain.models.photos.DomainSponsor
import com.cmaina.domain.models.photos.DomainSponsorLinks
import com.cmaina.domain.models.photos.DomainSponsorSocial
import com.cmaina.domain.models.photos.DomainUrls
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
import com.cmaina.domain.models.specificphoto.SpecificPhotoDomainModel
import com.cmaina.domain.models.specificphoto.TagDomainModel
import com.cmaina.domain.models.users.ProfileImageDomainModel
import com.cmaina.domain.models.users.TagsDomainModel
import com.cmaina.domain.models.users.UserDomainModel
import com.cmaina.domain.models.users.UserPhotoDomainModel
import com.cmaina.network.models.auth.AuthRemoteResponse
import com.cmaina.network.models.photos.PhotoLinks
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
import com.cmaina.network.models.specificphoto.Tag
import com.cmaina.network.models.specificphoto.Topic
import com.cmaina.network.models.users.Photo
import com.cmaina.network.models.users.Tags
import com.cmaina.network.models.users.UserDto

internal fun List<PhotoListItem>.toDomain() = this.map { it.toDomain() }

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
    linksDomain = links?.toDomain(),
    promotedAt = promoted_at,
    sponsorshipDomainModel = sponsorship?.toDomain(),
    updatedAt = updated_at,
    domainUrls = urls?.toDomain(),
    domainPhotoUser = user?.toDomain(),
    width = width
)

internal fun PhotoStatistics.toDomain() = DomainPhotoStatistics(
    id = id,
    domainPhotoStatLikes = likes.toDomain(),
    domainPhotoStatDownloads = downloads.toDomain(),
    domainPhotoStatsViews = views.toDomain()
)

internal fun Likes.toDomain() = DomainPhotoStatLikes(
    total = total,
)

internal fun Downloads.toDomain() = DomainPhotoStatDownloads(total = total)

internal fun Views.toDomain() = DomainPhotoStatsViews(total = total)

internal fun User.toDomain() = DomainPhotoUser(
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

internal fun Urls.toDomain() = DomainUrls(
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

internal fun Sponsor.toDomain() = DomainSponsor(
    acceptedTos = accepted_tos,
    bio = bio,
    firstName = first_name,
    forHire = for_hire,
    id = id,
    instagramUsername = instagram_username,
    lastName = last_name,
    domainSponsorLinks = sponsorLinks?.toDomain(),
    location = location,
    name = name,
    portfolioUrl = portfolio_url,
    domainProfileImage = profile_image?.toDomain(),
    domainSponsorSocial = sponsorSocial?.toDomainSponsorSocial(),
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
    domainSponsor = sponsor?.toDomain()
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
    profile_image = profile_image.toDomain(),
    social = social.toDomain(),
    tags?.toDomain(),
    total_collections,
    total_likes,
    total_photos,
    twitter_username,
    updated_at,
    username
)

internal fun com.cmaina.network.models.users.ProfileImage.toDomain() =
    ProfileImageDomainModel(large, medium, small)

internal fun Meta.toDomain() = MetaDomainModel(index = index)

internal fun Photo.toDomain() = UserPhotoDomainModel(
    blur_hash = blur_hash,
    id = id,
    created_at = created_at,
    updated_at = updated_at,
    urls = urls.toDomain()
)

internal fun SpecificPhoto.toDomain() = SpecificPhotoDomainModel(
    altDescription = alt_description,
    blurHash = blur_hash,
    categories = categories,
    color = color,
    createdAt = created_at,
    currentUserCollections = current_user_collections,
    description = description,
    downloads = downloads,
    height = height,
    id = id,
    likedByUser = liked_by_user,
    likes = likes,
    links = links?.toDomain(),
    locationDomainModel = location?.toDomain(),
    metaDomainModel = meta?.toDomain(),
    relatedCollectionsDomainModel = related_collections?.toDomain(),
    sponsorshipDomainModel = sponsorship?.toDomain(),
    tags = tags,
    topics = topics.map { it.toDomain() },
    urls = urls?.toDomain(),
    user = user?.toDomain(),
    views = views,
    width = width
)

internal fun RelatedCollections.toDomain() = RelatedCollectionsDomainModel(
    collectionDomainModels = this.results.map { it.toDomain() },
    total = total,
    type = type
)

internal fun Result.toDomain() = CollectionDomainModel(
    description = description,
    id = id,
    previewPhotoDomainModels = preview_photos.map { it.toDomain() },
    resultLinksDomainModel = links.toDomain(),
    title = title,
    user = user.toDomain(),
    totalPhotos = total_photos
)

internal fun Tag.toDomain() = TagDomainModel(source.toDomain(), title, type)

internal fun Tags.toDomain() =
    TagsDomainModel(aggregated = null, custom = null)

internal fun Source.toDomain() = com.cmaina.domain.models.specificphoto.Source(
    ancestryDomainModel = null,
    cover_photoDomainModel = null,
    description,
    meta_description,
    meta_title,
    subtitle,
    title
)

internal fun ResultLinks.toDomain() = ResultLinksDomainModel(html, photos, related, self)

internal fun PreviewPhoto.toDomain() = PreviewPhotoDomainModel(
    blur_hash = blur_hash,
    id = id,
    urls = urls.toDomain()
)

internal fun CoverPhoto.toDomain() = CoverPhotoDomainModel(
    alt_description = alt_description,
    blur_hash = blur_hash,
    categories = categories,
    color = color,
    created_at = created_at,
    description = description, height = height,
    id = id,
    liked_by_user = liked_by_user,
    likes = likes,
    links = links.toDomain(),
    urls = urls.toDomain(),
    user = user.toDomain(),
    width = width
)

internal fun Location.toDomain() = LocationDomainModel(
    city = city,
    country = country,
    name = name,
    positionDomainModel = position?.toDomain(),
    title = title
)

internal fun Position.toDomain() = PositionDomainModel(latitude, longitude)

internal fun Topic.toDomain() =
    com.cmaina.domain.models.specificphoto.Topic(id, slug, title, visibility)

internal fun PhotoSearchResultDto.toDomain() = PhotoSearchResultDomainModel(
    searchedPhotoDomainModels = results.map { it.toDomain() },
    total = total,
    totalPages = total_pages
)

internal fun SearchedPhotoDto.toDomain() = DomainPhotoListItem(
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
    sponsorshipDomainModel = sponsorship?.toDomain(),
    updatedAt = updated_at,
    domainUrls = urls.toDomain(),
    domainPhotoUser = user.toDomain(),
    width = width
)

internal fun AuthRemoteResponse.toDomain() = AuthDomainResponse(
    accessToken = accessToken,
    createdAt = createdAt,
    scope = scope,
    tokenType = tokenType,
    refreshToken = refreshToken
)
