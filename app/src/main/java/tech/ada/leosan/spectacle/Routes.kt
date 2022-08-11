package tech.ada.leosan.spectacle

sealed class Routes(val route: String) {
    object SignIn : Routes("SignIn")
    object SignUp : Routes("SignUp")
    object Main : Routes("Main")
    object Home : Routes("Home")
    object MusicLibrary : Routes("MusicLibrary")
    object AddMusic : Routes("AddMusic")
}