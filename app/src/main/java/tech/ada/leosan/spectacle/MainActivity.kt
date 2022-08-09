package tech.ada.leosan.spectacle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    val TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme(
                colors = CustomColors.Colors
            ) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    MaterialTheme.colors.secondary,
                                    MaterialTheme.colors.primary,
                                )
                            )
                        )
                ) {
                    val navController = rememberNavController()

                    NavHost(navController, startDestination = Routes.Main.route) {
                        composable(Routes.Main.route) {
                            MainScreen(
                                navigateToSignIn = {
                                    navController.navigate(Routes.SignIn.route) {
                                        popUpTo(Routes.Main.route) {
                                            inclusive = true
                                        }
                                    }
                                }
                            )
                        }
                        composable(Routes.SignIn.route) {
                            SignInScreen(
                                navigateToHome = {
                                    navController.navigate(Routes.Home.route)
                                },
                                navigateToSignUp = {
                                    navController.navigate(Routes.SignUp.route) {
                                        popUpTo(Routes.SignIn.route) {
                                            inclusive = true
                                        }
                                    }
                                }
                            )
                        }
                        composable(Routes.SignUp.route) {
                            SignUpScreen(
                                navigateToHome = {
                                    navController.navigate(Routes.Home.route)
                                },
                                navigateToSignIn = {
                                    navController.navigate(Routes.SignIn.route){
                                        popUpTo(Routes.SignUp.route) {
                                            inclusive = true
                                        }
                                    }
                                }
                            )
                        }
                        composable(Routes.Home.route) {
                            HomeScreen()
                        }
                    }
                }
            }
        }
    }
}