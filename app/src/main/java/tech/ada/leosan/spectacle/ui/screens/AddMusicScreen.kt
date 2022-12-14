package tech.ada.leosan.spectacle.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import tech.ada.leosan.spectacle.R
import tech.ada.leosan.spectacle.model.Track
import tech.ada.leosan.spectacle.ui.components.TrackComponent
import tech.ada.leosan.spectacle.ui.states.SearchMusicDataState
import tech.ada.leosan.spectacle.ui.viewmodel.AddMusicViewModel

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
        AddMusicSearchBar(viewModel)

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
                is SearchMusicDataState.Success -> {
                    TrackListAdd(
                        tracks = (state as SearchMusicDataState.Success).data,
                        navController = navController
                    )
                }
                is SearchMusicDataState.Failure -> {
                    Text("Error! ${(state as SearchMusicDataState.Failure).message}")
                }
            }
        }
    }
}

@Composable
fun AddMusicSearchBar(
    viewModel: AddMusicViewModel
) {
    var searchContent by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .padding(horizontal = 48.dp)
    ) {
        TextField(
            value = searchContent,
            onValueChange = {
                searchContent = it
                if (searchContent.isNotEmpty()) {
                    viewModel.search(it)
                } else {
                    viewModel.getTopChartMusic()
                }
            },
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
fun TrackListAdd(
    tracks: MutableList<Track>,
    navController: NavHostController
) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 24.dp)
    ) {
        items(items = tracks) { track ->
            TrackComponent(track,
                addElement = true,
                addElementAction = {
                    val uid = Firebase.auth.uid
                    val ref = Firebase.database.getReference("$uid/musiclist/")
                    ref.push().setValue(track)

                    navController.popBackStack()
                }
            )
        }
    }
}
