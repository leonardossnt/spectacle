package tech.ada.leosan.spectacle.model

import com.google.gson.annotations.SerializedName

data class Track(
    @SerializedName("")
    val title: String = "",
    val artist: String = "",
    val thumbnailUrl: String? = ""
)
