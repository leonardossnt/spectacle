package tech.ada.leosan.spectacle

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun SongComponentPreview() {
    SongComponent(
        Song("Caneta e Papel", "Os Arrais", "loooool.png")
    )
}

@Composable
fun SongComponent(
    song: Song
) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                imageVector = Icons.Default.MusicNote,
                contentDescription = null,
                Modifier
                    .background(
                        Color.White
                    )
                    .size(72.dp)
            )
            Spacer(Modifier.width(16.dp))
            Column() {
                Text(
                    song.title,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                    ),
                )
                Text(
                    song.artist,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light,
                    ),
                )
            }
        }
    }
}