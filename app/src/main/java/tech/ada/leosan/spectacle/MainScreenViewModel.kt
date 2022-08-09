package tech.ada.leosan.spectacle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class MainScreenState {
    object Loading : MainScreenState()
    object SignUp : MainScreenState()
    object SignInRequired : MainScreenState()
    object LoggedIn : MainScreenState()
}

class MainScreenViewModel : ViewModel() {
    private val mutableState = MutableStateFlow<MainScreenState>(MainScreenState.Loading)

    val state = mutableState.asStateFlow()

    init {
        viewModelScope.launch {
            // TODO check for user credentials
            mutableState.emit(MainScreenState.SignInRequired)
        }
    }
}
