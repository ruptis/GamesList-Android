package by.bsuir.gameslist.ui.screens.home.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.bsuir.gameslist.model.Game
import by.bsuir.gameslist.model.Status
import by.bsuir.gameslist.ui.components.GameActionsView
import by.bsuir.gameslist.ui.components.Tag
import coil.compose.AsyncImage

@Composable
fun GameCardView(
    game: Game,
    onClick: () -> Unit,
    onStatusChange: (Status) -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        onClick = onClick,
        modifier = modifier.height(160.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            AsyncImage(
                model = game.cover,
                contentDescription = "cover",
                modifier = Modifier
                    .background(color = Color.Gray, shape = MaterialTheme.shapes.medium)
                    .width(120.dp)
                    .fillMaxHeight()
                    .clip(MaterialTheme.shapes.medium)
            )

            Column(
                modifier = modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = game.title,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    modifier = modifier.fillMaxWidth()
                )

                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        game.genres.forEach { genre ->
                            Tag(
                                text = genre,
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        game.platforms.forEach { platform ->
                            Tag(
                                text = platform.abbreviation,
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                    }
                }

                GameActionsView(
                    status = game.status,
                    onStatusChange = onStatusChange,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp)
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(name = "GameCard", showBackground = true)
fun PreviewGameCard() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        GameCardView(game = Game.mockGame(), onClick = {}, onStatusChange = {})
    }
}