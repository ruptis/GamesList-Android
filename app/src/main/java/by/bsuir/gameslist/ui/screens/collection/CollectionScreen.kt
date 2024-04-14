package by.bsuir.gameslist.ui.screens.collection

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import by.bsuir.gameslist.model.Game
import by.bsuir.gameslist.model.Response
import by.bsuir.gameslist.model.Status
import by.bsuir.gameslist.ui.components.GameDetailsView
import by.bsuir.gameslist.ui.screens.collection.components.CollectionListView
import by.bsuir.gameslist.ui.theme.GamesListTheme

@Composable
fun CollectionScreen(
    modifier: Modifier = Modifier,
    viewModel: CollectionViewModel = hiltViewModel()
) {
    val tabs by viewModel.games.collectAsStateWithLifecycle()
    val selectedGame by viewModel.selectedGame.collectAsStateWithLifecycle()

    CollectionScreen(
        tabsStates = tabs,
        selectedGame = selectedGame,
        onSelectGame = viewModel::selectGame,
        onStatusChange = viewModel::changeGameStatus,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
private fun CollectionScreen(
    tabsStates: List<CollectionTabState>,
    selectedGame: Game?,
    onSelectGame: (Game) -> Unit,
    onStatusChange: (Game, Status) -> Unit,
    modifier: Modifier = Modifier
) {
    val navigator = rememberListDetailPaneScaffoldNavigator()
    val listState = rememberLazyListState()
    val pagerState = rememberPagerState(pageCount = { tabsStates.size })

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            AnimatedPane {
                CollectionListView(
                    tabsStates = tabsStates,
                    onRowClick = {
                        onSelectGame(it)
                        navigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
                    },
                    onStatusChange = onStatusChange,
                    modifier = modifier,
                    listState = listState,
                    pagerState = pagerState
                )
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
@Preview(name = "CollectionScreen", showBackground = true)
@Composable
private fun PreviewCollectionScreen() {
    val games by remember {
        mutableStateOf((1..10).map { Game.mockGame(it.toString(), Status.Playing) })
    }
    val tabs = CollectionTab.entries.map { tab ->
        CollectionTabState(
            tab = tab,
            games = Response.Success(games.filter { it.status == tab.status })
        )
    }

    GamesListTheme {
        CollectionScreen(
            tabsStates = tabs,
            selectedGame = games[0],
            onSelectGame = {},
            onStatusChange = { _, _ -> }
        )
    }
}