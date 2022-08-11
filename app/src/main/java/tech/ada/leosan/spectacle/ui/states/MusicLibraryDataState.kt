package tech.ada.leosan.spectacle.ui.states

import tech.ada.leosan.spectacle.model.Track

sealed class MusicLibraryDataState {
    class Success(val data: MutableList<Track>) : MusicLibraryDataState()
    class Failure(val message: String) : MusicLibraryDataState()
    object Loading : MusicLibraryDataState()
    object Empty : MusicLibraryDataState()
}