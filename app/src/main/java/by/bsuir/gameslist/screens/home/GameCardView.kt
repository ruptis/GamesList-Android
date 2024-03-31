package by.bsuir.gameslist.screens.home

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.bsuir.gameslist.R
import by.bsuir.gameslist.model.Game
import by.bsuir.gameslist.model.Platform

@Composable
fun GameCardView(
    game: Game,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        onClick = { /*TODO*/ },
        modifier = modifier.height(160.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Box(
                modifier = modifier
                    .background(Color.Gray, MaterialTheme.shapes.medium)
                    .width(120.dp)
                    .fillMaxHeight()
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
                            Surface(
                                shape = MaterialTheme.shapes.small,
                                color = Color.Transparent,
                                border = BorderStroke(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            ) {
                                Text(
                                    text = genre,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    style = MaterialTheme.typography.labelMedium,
                                    modifier = Modifier.padding(4.dp)
                                )
                            }
                        }
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        game.platformsData?.forEach { platform ->
                            Surface(
                                shape = MaterialTheme.shapes.small,
                                color = Color.Transparent,
                                border = BorderStroke(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            ) {
                                Text(
                                    text = platform.abbreviation,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    style = MaterialTheme.typography.labelMedium,
                                    modifier = Modifier.padding(4.dp)
                                )
                            }
                        }
                    }
                }

                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.gamepad),
                            contentDescription = "Playing",
                            modifier = modifier.fillMaxSize()
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.clock),
                            contentDescription = "Planning",
                            modifier = modifier.fillMaxSize()
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.check_circle),
                            contentDescription = "Passed",
                            modifier = modifier.fillMaxSize()
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.stop_circle),
                            contentDescription = "Abandoned",
                            modifier = modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(name = "GameCard", showBackground = true)
fun PreviewGameCard() {
    val game = Game(
        id = "0",
        title = "Game Title",
        releaseDate = "3/31/2024",
        description = "desc",
        platforms = listOf("0"),
        genres = listOf("Action", "Adventure"),
        developer = "Developer",
        publisher = "Publisher",
        screenshots = listOf(""),
        cover = "",
        platformsData = listOf(
            Platform("0", "PC", "PC"),
            Platform("1", "PlayStation 5", "PS5")
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        GameCardView(game = game)
    }
}