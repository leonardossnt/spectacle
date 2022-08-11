package tech.ada.leosan.spectacle

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

sealed class SearchMusicDataState {
    class Success(val data: MutableList<Track>) : SearchMusicDataState()
    class Failure(val message: String) : SearchMusicDataState()
    object Loading : SearchMusicDataState()
    object Empty : SearchMusicDataState()
}

class AddMusicViewModel : ViewModel() {
    private val mutableState = MutableStateFlow<SearchMusicDataState>(SearchMusicDataState.Empty)
    val state = mutableState.asStateFlow()

    init {
        getTopChartMusic()
    }

    fun getTopChartMusic() {
        mutableState.value = SearchMusicDataState.Loading

        val musicApi = RetrofitClient().getInstance().getMusicApi()

        musicApi.getTopChartMusic().enqueue(object : Callback<DeezerDTO> {
            override fun onResponse(call: Call<DeezerDTO>, response: Response<DeezerDTO>) {
                if (response.isSuccessful) {
                    val list = response.body()?.data?.map { mapDtoToTrack(it) }
                    val tracks = list?.toMutableList()
                    if (!tracks.isNullOrEmpty()) {
                        mutableState.value = SearchMusicDataState.Success(tracks)
                    } else {
                        mutableState.value = SearchMusicDataState.Empty
                    }
                }
            }

            override fun onFailure(call: Call<DeezerDTO>, t: Throwable) {
                println(t.stackTraceToString())
            }
        })
    }

    fun search(search: String) {
        mutableState.value = SearchMusicDataState.Loading

        val musicApi = RetrofitClient().getInstance().getMusicApi()

        musicApi.searchMusic(search).enqueue(object : Callback<DeezerDTO> {
            override fun onResponse(call: Call<DeezerDTO>, response: Response<DeezerDTO>) {
                if (response.isSuccessful) {
                    val list = response.body()?.data?.map { mapDtoToTrack(it) }
                    val tracks = list?.toMutableList()
                    if (!tracks.isNullOrEmpty()) {
                        mutableState.value = SearchMusicDataState.Success(tracks)
                    } else {
                        mutableState.value = SearchMusicDataState.Empty
                    }
                }
            }

            override fun onFailure(call: Call<DeezerDTO>, t: Throwable) {
                println(t.stackTraceToString())
            }
        })
    }
}