package by.bsuir.gameslist.screens.collection.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.bsuir.gameslist.model.Game
import by.bsuir.gameslist.ui.components.GameActionsView
import by.bsuir.gameslist.ui.components.Tag

@Composable
fun GameRowView(
    game: Game,
    onClick: () -> Unit,
    onStatusChange: (Game.Status) -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        onClick = onClick,
        modifier = modifier.height(88.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = modifier
                    .background(Color.Gray, MaterialTheme.shapes.medium)
                    .width(66.dp)
                    .fillMaxHeight()
            )

            Column(
                modifier = modifier.padding(5.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = game.title,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )

                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        game.genres.forEach { genre ->
                            Tag(
                                text = genre,
                                modifier = modifier.padding(2.dp),
                                textStyle = MaterialTheme.typography.labelSmall,
                                borderWidth = 0.5.dp
                            )
                        }
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        game.platformsData?.forEach { platform ->
                            Tag(
                                text = platform.abbreviation,
                                modifier = modifier.padding(2.dp),
                                textStyle = MaterialTheme.typography.labelSmall,
                                borderWidth = 0.5.dp
                            )
                        }
                    }
                }
            }

            GameActionsView(
                status = game.status,
                onStatusChange = onStatusChange
            )
        }
    }
}

@Preview(name = "GameRowView")
@Composable
private fun PreviewGameRowView() {
    GameRowView(
        game = Game.mockGame(),
        onClick = {},
        onStatusChange = {}
    )
}