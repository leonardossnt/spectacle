package tech.ada.leosan.spectacle.model.dto

import com.google.gson.annotations.SerializedName
import tech.ada.leosan.spectacle.model.Track

data class DeezerDTO(
    @SerializedName("data")
    val data: List<TrackDTO>?,
)

data class TrackDTO(
    @SerializedName("title")
    val trackTitle: String,
    @SerializedName("artist")
    val artist: ArtistDTO,
    @SerializedName("album")
    val album: AlbumDTO
)

data class ArtistDTO(
    @SerializedName("name")
    val name: String
)

data class AlbumDTO(
    @SerializedName("title")
    val title: String,
    @SerializedName("cover_medium")
    val thumbnailUrl: String
)

fun mapDtoToTrack(dto: TrackDTO): Track {
    return Track(
        title = dto.trackTitle,
        artist = dto.artist.name,
        thumbnailUrl = dto.album.thumbnailUrl
    )
}