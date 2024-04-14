package by.bsuir.gameslist.ui.screens.home

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import by.bsuir.gameslist.model.Game
import by.bsuir.gameslist.model.Status
import by.bsuir.gameslist.ui.components.GameDetailsView
import by.bsuir.gameslist.ui.components.GameListView
import by.bsuir.gameslist.ui.components.ResponseView
import by.bsuir.gameslist.ui.screens.home.components.GameCardView

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val gamesResponse by viewModel.games.collectAsStateWithLifecycle()
    val selectedGame by viewModel.selectedGame.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()

    ResponseView(
        response = gamesResponse,
        modifier = modifier.fillMaxSize()
    ) {
        HomeScreen(
            games = it,
            selectedGame = selectedGame,
            onSelectGame = viewModel::selectGame,
            onStatusChange = viewModel::changeGameStatus,
            modifier = modifier,
            listState = listState
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
fun HomeScreen(
    games: List<Game>,
    selectedGame: Game?,
    onSelectGame: (Game) -> Unit,
    onStatusChange: (Game, Status) -> Unit,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState()
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
                GameListView(
                    games = games,
                    listState = listState
                ) { game ->
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

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "HomeScreen", showBackground = true)
@Composable
fun PreviewHomeScreen() {
    val games = (0..<10).map { Game.mockGame(it.toString()) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        HomeScreen(
            games = games,
            selectedGame = null,
            onSelectGame = { },
            onStatusChange = { _, _ -> }
        )
    }


}