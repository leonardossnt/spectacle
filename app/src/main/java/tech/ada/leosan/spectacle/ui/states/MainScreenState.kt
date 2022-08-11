package tech.ada.leosan.spectacle.ui.states

sealed class MainScreenState {
    object Loading : MainScreenState()
    object SignUp : MainScreenState()
    object SignInRequired : MainScreenState()
    object LoggedIn : MainScreenState()
}