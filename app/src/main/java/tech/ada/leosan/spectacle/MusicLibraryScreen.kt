package tech.ada.leosan.spectacle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Preview
@Composable
fun MusicLibraryScreenPreview() {
    MusicLibraryScreen(rememberNavController())
}

@Composable
fun MusicLibraryScreen(
    navController: NavHostController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        MusicLibraryTopBar(navController)
        MusicLibrarySearchBar {}
        MusicLibraryMosaic()
        MusicLibraryTitle()
        AddMusicButton()
    }
}

@Composable
fun MusicLibraryTopBar(
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth(),
    ) {
        IconButton(
            modifier = Modifier.align(Alignment.TopStart),
            onClick = {
                navController.popBackStack()
            }) {
            Icon(
                Icons.Rounded.ArrowBack,
                contentDescription = stringResource(R.string.back),
                tint = MaterialTheme.colors.onPrimary
            )
        }
    }
}

@Composable
fun MusicLibrarySearchBar(
    onValueChange: (String) -> Unit,
) {
    var searchContent by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .padding(horizontal = 48.dp)
    ) {
        TextField(
            value = searchContent,
            onValueChange = { searchContent = it },
            placeholder = {
                Text(
                    stringResource(R.string.search_placeholder),
                )
            },

            // layout
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50.dp),
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null
                )
            },

            //style
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                leadingIconColor = MaterialTheme.colors.onPrimary,
                placeholderColor = MaterialTheme.colors.onPrimary.copy(alpha = 0.5f),
                textColor = MaterialTheme.colors.onPrimary,
                cursorColor = MaterialTheme.colors.onPrimary
            ),
        )
    }
}

@Composable
fun MusicLibraryMosaic() {
    Box(
        Modifier
            .padding(32.dp)
            .size(240.dp)
            .background(Color.White.copy(alpha = 0.4f))
    ) {
        Text("Mosaic here", modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun MusicLibraryTitle() {
    Text(
        text = stringResource(R.string.my_playlist),
        style = TextStyle(
            color = MaterialTheme.colors.onPrimary,
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center
        ),
    )
}

@Composable
fun AddMusicButton() {
    Button(
        onClick = {  },
        modifier = Modifier.wrapContentWidth().padding(16.dp),
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(2.dp)
        ) {
            Icon(
                Icons.Rounded.Add,
                contentDescription = null,
                tint = MaterialTheme.colors.primary,
            )
            Spacer(Modifier.width(8.dp))
            Text(
                stringResource(R.string.add_new_song),
            )

        }
    }
}