package tech.ada.leosan.spectacle

sealed class Routes(val route: String) {
    object Login : Routes("Login")
    object SignUp : Routes("SignUp")
    object Main : Routes("Main")
}