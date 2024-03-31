package by.bsuir.gameslist.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.bsuir.gameslist.model.Game
import by.bsuir.gameslist.model.Platform

@Composable
fun HomeScreen(
    games: List<Game>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(games) { game ->
            GameCardView(game = game)
        }
    }
}

@Preview(name = "HomeScreen", showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    HomeScreen(games = (1..10).map { mockGame() })
}

fun mockGame(id: String = "0"): Game {
    return Game(
        id = id,
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
}