package by.bsuir.gameslist.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.bsuir.gameslist.model.Game
import by.bsuir.gameslist.screens.home.components.GameCardView

@Composable
fun GameListView(
    games: List<Game>,
    modifier: Modifier = Modifier,
    item: @Composable (Game) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(games) { game ->
            item(game)
        }
    }
}

@Preview(name = "GameListView", showBackground = true)
@Composable
private fun PreviewGameListView() {
    GameListView(games = (1..10).map { Game.mockGame() }) {
        GameCardView(game = it, onClick = { }, onStatusChange = { })
    }
}