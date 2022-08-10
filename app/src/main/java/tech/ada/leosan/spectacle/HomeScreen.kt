package tech.ada.leosan.spectacle

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen {}
}

@Composable
fun HomeScreen(
    navigateToMain: () -> Unit
) {
    HomeScreenTopBar(navigateToMain)
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.what_do_you_want),
            style = TextStyle(
                color = MaterialTheme.colors.onPrimary,
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            ),
        )
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

