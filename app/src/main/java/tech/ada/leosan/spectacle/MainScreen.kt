package tech.ada.leosan.spectacle

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MainScreen(
    navigateToSignIn: () -> Unit
) {
    val viewModel = viewModel<MainScreenViewModel>()
    val state by viewModel.state.collectAsState()

    when (state) {
        MainScreenState.SignInRequired -> LaunchedEffect(Unit) {
            navigateToSignIn()
        }
        MainScreenState.SignUp -> TODO()
        MainScreenState.Loading -> LoadingScreen()
        MainScreenState.LoggedIn -> HomeScreen()
    }
}
