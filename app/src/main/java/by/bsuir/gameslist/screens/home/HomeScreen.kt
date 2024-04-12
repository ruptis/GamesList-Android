package by.bsuir.gameslist.screens.home

import androidx.activity.compose.BackHandler
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import by.bsuir.gameslist.model.Game
import by.bsuir.gameslist.model.Response
import by.bsuir.gameslist.screens.home.components.GameCardView
import by.bsuir.gameslist.ui.components.GameDetailsView
import by.bsuir.gameslist.ui.components.GameListView

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val games by viewModel.games.collectAsStateWithLifecycle()
    val selectedGame by viewModel.selectedGame.collectAsStateWithLifecycle()

    when (val response = games) {
        is Response.Loading -> {
            // Loading state
        }
        is Response.Error -> {
            // Error state
        }
        is Response.Success -> {
            HomeScreen(
                games = response.data,
                selectedGame = selectedGame,
                onSelectGame = viewModel::selectGame,
                onStatusChange = viewModel::changeGameStatus,
                modifier = modifier
            )
        }
    }

}

@Composable
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
fun HomeScreen(
    games: List<Game>,
    selectedGame: Game?,
    onSelectGame: (Game) -> Unit,
    onStatusChange: (Game, Game.Status) -> Unit,
    modifier: Modifier = Modifier
) {
    val navigator = rememberListDetailPaneScaffoldNavigator()

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            AnimatedPane {
                GameListView(games = games) { game ->
                    GameCardView(
                        game = game,
                        onClick = {
                            onSelectGame(game)
                            navigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
                        },
                        onStatusChange = { onStatusChange(game, it) }
                    )
                }
            }
        },
        detailPane = {
            AnimatedPane {
                selectedGame?.let { game ->
                    GameDetailsView(
                        game = game,
                        onStatusChange = { onStatusChange(game, it) }
                    )
                }
            }
        },
        modifier = modifier
    )
}

@Preview(name = "HomeScreen", showBackground = true)
@Composable
fun PreviewHomeScreen() {
    val games = (0..<10).map { Game.mockGame(it.toString()) }

    HomeScreen(
        games = games,
        selectedGame = null,
        onSelectGame = { },
        onStatusChange = { _, _ -> }
    )
}