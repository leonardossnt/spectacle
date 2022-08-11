package tech.ada.leosan.spectacle.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import tech.ada.leosan.spectacle.ui.states.MainScreenState

class MainScreenViewModel : ViewModel() {
    private val mutableState = MutableStateFlow<MainScreenState>(MainScreenState.Loading)

    val state = mutableState.asStateFlow()

    init {
        viewModelScope.launch {
            if (Firebase.auth.currentUser != null) {
                mutableState.emit(MainScreenState.LoggedIn)
            } else {
                mutableState.emit(MainScreenState.SignInRequired)
            }
        }
    }
}
