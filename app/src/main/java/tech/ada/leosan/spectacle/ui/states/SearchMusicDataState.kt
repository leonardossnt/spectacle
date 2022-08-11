package tech.ada.leosan.spectacle.ui.states

import tech.ada.leosan.spectacle.model.Track

sealed class SearchMusicDataState {
    class Success(val data: MutableList<Track>) : SearchMusicDataState()
    class Failure(val message: String) : SearchMusicDataState()
    object Loading : SearchMusicDataState()
    object Empty : SearchMusicDataState()
}