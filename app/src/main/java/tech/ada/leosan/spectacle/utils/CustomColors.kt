package tech.ada.leosan.spectacle.utils

import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

class CustomColors {
    companion object {
        val LightPurple = Color(0xffb887e5)
        val LightBlue = Color(0xff7ab1d4)

        val Colors = lightColors(
            primary = LightPurple,
            secondary = LightBlue,
        )
    }
}