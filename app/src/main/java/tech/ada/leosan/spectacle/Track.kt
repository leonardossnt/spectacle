package tech.ada.leosan.spectacle

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Track(
    @SerializedName("")
    val title: String = "",
    val artist: String = "",
    val thumbnailUrl: String = ""
) : Serializable
