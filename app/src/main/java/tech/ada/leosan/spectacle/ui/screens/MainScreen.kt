package tech.ada.leosan.spectacle.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import tech.ada.leosan.spectacle.ui.states.MainScreenState
import tech.ada.leosan.spectacle.ui.viewmodel.MainScreenViewModel

@Composable
fun MainScreen(
    navigateToSignIn: () -> Unit,
    navigateToHome: () -> Unit
) {
    val viewModel = viewModel<MainScreenViewModel>()
    val state by viewModel.state.collectAsState()

    when (state) {
        MainScreenState.SignInRequired -> LaunchedEffect(Unit) {
            navigateToSignIn()
        }
        MainScreenState.SignUp -> TODO()
        MainScreenState.Loading -> LoadingScreen()
        MainScreenState.LoggedIn -> navigateToHome()
    }
}
