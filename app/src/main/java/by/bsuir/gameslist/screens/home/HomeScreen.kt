package by.bsuir.gameslist.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import by.bsuir.gameslist.model.Game
import by.bsuir.gameslist.screens.home.components.GameCardView

@Composable
fun HomeScreen(
    onGameCardClick: (Game) -> Unit,
    modifier: Modifier = Modifier,
    nestedScrollConnection: NestedScrollConnection = rememberNestedScrollInteropConnection(),
    viewModel: HomeViewModel = hiltViewModel()
) {
    val games by viewModel.games.collectAsState(emptyList())

    HomeScreen(
        games = games,
        onCardClick = onGameCardClick,
        onStatusChange = viewModel::changeGameStatus,
        nestedScrollConnection = nestedScrollConnection,
        modifier = modifier
    )
}

@Composable
fun HomeScreen(
    games: List<Game>,
    onCardClick: (Game) -> Unit,
    onStatusChange: (Game, Game.Status) -> Unit,
    modifier: Modifier = Modifier,
    nestedScrollConnection: NestedScrollConnection = rememberNestedScrollInteropConnection()
) {
    LazyColumn(
        modifier = modifier.nestedScroll(nestedScrollConnection),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(games) { game ->
            GameCardView(
                game = game,
                onClick = { onCardClick(game) },
                onStatusChange = { status -> onStatusChange(game, status) }
            )
        }
    }
}

@Preview(name = "HomeScreen", showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    HomeScreen(
        games = (1..10).map { Game.mockGame() },
        onCardClick = {},
        onStatusChange = { _: Game, _: Game.Status -> },
    )
}