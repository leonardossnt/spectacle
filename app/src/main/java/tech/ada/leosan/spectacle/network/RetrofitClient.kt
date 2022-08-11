package tech.ada.leosan.spectacle.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        private var instance: RetrofitClient? = null
        const val baseUrl = "https://api.deezer.com/"
    }

    private var musicApiService : MusicApiService

    init {
        val gson = GsonBuilder().create()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(baseUrl)
            .build()

        musicApiService = retrofit.create(MusicApiService::class.java)
    }

    @Synchronized
    fun getInstance() : RetrofitClient {
        if (instance == null) {
            instance = RetrofitClient()
        }
        return instance as RetrofitClient
    }

    fun getMusicApi() = musicApiService
}