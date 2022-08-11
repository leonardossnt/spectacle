package tech.ada.leosan.spectacle.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircleOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import tech.ada.leosan.spectacle.model.Track

@Preview
@Composable
fun TrackComponentPreview() {
    TrackComponent(
        Track("Caneta e Papel", "Os Arrais", "loooool.png")
    )
}

@Composable
fun TrackComponent(
    track: Track,
    addElement: Boolean = false,
    addElementAction: () -> Unit = {}
) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = track.thumbnailUrl,
                contentDescription = null,
                Modifier.size(72.dp)
            )
            Spacer(Modifier.width(16.dp))
            Column(
                Modifier.weight(1f)
            ) {
                Text(
                    track.title,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                    ),
                )
                Text(
                    track.artist,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light,
                    ),
                )
            }
            if (addElement) {
                Spacer(Modifier.width(16.dp))
                IconButton(onClick = addElementAction) {
                    Icon(imageVector = Icons.Rounded.AddCircleOutline, contentDescription = null)
                }
            }
        }
    }
}