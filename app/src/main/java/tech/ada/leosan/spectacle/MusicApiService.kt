package tech.ada.leosan.spectacle

import retrofit2.Call
import retrofit2.http.GET

interface MusicApiService {

    @GET("chart/0/tracks")
    fun listMusic(): Call<DeezerDTO>

    @GET("search?q={search}")
    fun searchMusic(search: String): Call<DeezerDTO>
}