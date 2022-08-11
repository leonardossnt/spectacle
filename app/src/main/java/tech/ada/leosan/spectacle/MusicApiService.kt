package tech.ada.leosan.spectacle

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicApiService {

    @GET("chart/0/tracks")
    fun getTopChartMusic(): Call<DeezerDTO>

    @GET("search")
    fun searchMusic(@Query("q") search: String): Call<DeezerDTO>
}