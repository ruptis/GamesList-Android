package by.bsuir.gameslist.screens.collection

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import by.bsuir.gameslist.model.Game
import by.bsuir.gameslist.model.Response
import by.bsuir.gameslist.model.Status
import by.bsuir.gameslist.screens.collection.components.GameRowView
import by.bsuir.gameslist.ui.components.GameDetailsView
import by.bsuir.gameslist.ui.components.GameListView
import by.bsuir.gameslist.ui.theme.GamesListTheme
import kotlinx.coroutines.launch

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

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun CollectionListView(
    tabsStates: List<CollectionTabState>,
    onRowClick: (Game) -> Unit,
    onStatusChange: (Game, Status) -> Unit,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    pagerState: PagerState = rememberPagerState(pageCount = { tabsStates.size })
) {
    val scope = rememberCoroutineScope()

    Column(modifier = modifier.fillMaxSize()) {
        SecondaryTabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = modifier
        ) {
            tabsStates.forEachIndexed { index, tabState ->
                val selected = pagerState.currentPage == index
                Tab(icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = if (selected) tabState.tab.selectedIcon else tabState.tab.icon
                        ),
                        contentDescription = null
                    )
                },
                    selected = selected,
                    onClick = { scope.launch { pagerState.animateScrollToPage(index) } })
            }
        }

        HorizontalPager(
            state = pagerState, modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            verticalAlignment = Alignment.Top
        ) { page ->
            val tab = tabsStates[page]
            when (val games = tab.games) {
                is Response.Loading -> CircularProgressIndicator()
                is Response.Success -> {
                    GameListView(
                        games = games.data,
                        listState = listState
                    ) { game ->
                        GameRowView(
                            game = game,
                            onClick = { onRowClick(game) },
                            onStatusChange = { onStatusChange(game, it) }
                        )
                    }
                }

                is Response.Error -> Unit
            }
        }
    }
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