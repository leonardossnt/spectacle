package tech.ada.leosan.spectacle.ui

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
import tech.ada.leosan.spectacle.*
import tech.ada.leosan.spectacle.navigation.Routes
import tech.ada.leosan.spectacle.ui.screens.*
import tech.ada.leosan.spectacle.utils.CustomColors

class MainActivity : ComponentActivity() {
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
                                },
                                navigateToHome = {
                                    navController.navigate(Routes.Home.route) {
                                        popUpTo(Routes.SignIn.route) {
                                            inclusive = true
                                        }
                                    }
                                }
                            )
                        }
                        composable(Routes.SignIn.route) {
                            SignInScreen(
                                navigateToHome = {
                                    navController.navigate(Routes.Home.route) {
                                        popUpTo(Routes.SignIn.route) {
                                            inclusive = true
                                        }
                                    }
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
                                    navController.navigate(Routes.Home.route) {
                                        popUpTo(Routes.SignUp.route) {
                                            inclusive = true
                                        }
                                    }
                                },
                                navigateToSignIn = {
                                    navController.navigate(Routes.SignIn.route) {
                                        popUpTo(Routes.SignUp.route) {
                                            inclusive = true
                                        }
                                    }
                                }
                            )
                        }
                        composable(Routes.Home.route) {
                            HomeScreen(
                                navigateToMain = {
                                    navController.navigate(Routes.SignIn.route) {
                                        popUpTo(Routes.Home.route) {
                                            inclusive = true
                                        }
                                    }
                                },
                                navigateToMusicLibrary = {
                                    navController.navigate(Routes.MusicLibrary.route)
                                }
                            )
                        }
                        composable(Routes.MusicLibrary.route) {
                            MusicLibraryScreen(navController,
                                navigateToAddMusic = {
                                    navController.navigate(Routes.AddMusic.route)
                                })
                        }
                        composable(Routes.AddMusic.route) {
                            AddMusicScreen(navController)
                        }
                    }
                }
            }
        }
    }
}