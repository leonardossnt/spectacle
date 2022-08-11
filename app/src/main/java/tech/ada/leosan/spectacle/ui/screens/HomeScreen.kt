package tech.ada.leosan.spectacle.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LibraryMusic
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import tech.ada.leosan.spectacle.R

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navigateToMain = {},
        navigateToMusicLibrary = {}
    )
}

@Composable
fun HomeScreen(
    navigateToMain: () -> Unit,
    navigateToMusicLibrary: () -> Unit
) {
    HomeScreenTopBar(navigateToMain)
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 36.dp)
    ) {
        HomeScreenTitle()

        Spacer(Modifier.height(32.dp))

        MusicButton { navigateToMusicLibrary() }

        Spacer(Modifier.height(24.dp))

        MoviesButton()
    }
}

@Composable
fun HomeScreenTopBar(
    navigateToMain: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth(),
    ) {
        Text(
            stringResource(R.string.app_name).uppercase(),
            modifier = Modifier.align(Alignment.Center),
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                fontFamily = FontFamily.Monospace
            )
        )

        IconButton(
            modifier = Modifier.align(Alignment.TopEnd),
            onClick = {
                Firebase.auth.signOut()
                navigateToMain()
            }) {
            Icon(
                Icons.Rounded.Logout,
                contentDescription = stringResource(R.string.logout),
                tint = Color.White
            )
        }
    }
}

@Composable
fun HomeScreenTitle() {
    Text(
        text = stringResource(R.string.what_do_you_want),
        style = TextStyle(
            color = MaterialTheme.colors.onPrimary,
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center
        ),
    )
}

@Composable
fun MusicButton(
    navigateToMusicLibrary: () -> Unit
) {
    CustomButton(
        label = stringResource(R.string.music),
        description = stringResource(R.string.music_description),
        icon = Icons.Rounded.LibraryMusic,
        onClick = navigateToMusicLibrary
    )
}

@Composable
fun MoviesButton() {
    CustomButton(
        label = stringResource(R.string.movies),
        description = stringResource(R.string.movies_description),
        icon = Icons.Rounded.Movie,
        enabled = false
    )
}

@Composable
fun CustomButton(
    label: String,
    description: String,
    icon: ImageVector,
    onClick: () -> Unit = {},
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(50.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
        enabled = enabled
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            Icon(
                icon,
                contentDescription = label,
                tint = MaterialTheme.colors.primary,
                modifier = Modifier
                    .size(56.dp)
            )
            Spacer(Modifier.width(24.dp))
            Column(
                Modifier.weight(1f)
            ) {
                Text(
                    label,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
                Text(
                    description,
                    style = TextStyle(
                        fontWeight = FontWeight.Light
                    )
                )
            }
        }
    }
}
