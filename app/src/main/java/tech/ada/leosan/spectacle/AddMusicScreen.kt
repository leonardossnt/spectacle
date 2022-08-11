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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Preview
@Composable
fun AddMusicScreenPreview() {
    AddMusicScreen(rememberNavController())
}

@Composable
fun AddMusicScreen(navController: NavHostController) {
    val viewModel = viewModel<AddMusicViewModel>()
    val state by viewModel.state.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        MusicLibraryTopBar(navController)
        AddMusicSearchBar {}

        Spacer(Modifier.height(16.dp))

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (state) {
                SearchMusicDataState.Empty -> {
                    Text(
                        stringResource(R.string.song_list_empty),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 32.dp)
                    )
                }
                SearchMusicDataState.Loading -> {
                    CircularProgressIndicator(color = Color.White)
                }
                is SearchMusicDataState.Success -> TrackList((state as SearchMusicDataState.Success).data)
                is SearchMusicDataState.Failure -> {
                    Text("Error! ${(state as SearchMusicDataState.Failure).message}")
                }
            }
        }
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