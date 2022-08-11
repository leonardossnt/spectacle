package tech.ada.leosan.spectacle

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Preview
@Composable
fun AddMusicScreenPreview() {
    AddMusicScreen(rememberNavController())
}

@Composable
fun AddMusicScreen(navController: NavHostController) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        MusicLibraryTopBar(navController)
        AddMusicSearchBar {}

        SongList(mutableListOf())
    }
}

@Composable
fun AddMusicSearchBar(
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