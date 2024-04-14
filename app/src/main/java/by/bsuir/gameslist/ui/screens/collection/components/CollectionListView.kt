package by.bsuir.gameslist.ui.screens.collection.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import by.bsuir.gameslist.model.Game
import by.bsuir.gameslist.model.Status
import by.bsuir.gameslist.ui.components.GameListView
import by.bsuir.gameslist.ui.components.ResponseView
import by.bsuir.gameslist.ui.screens.collection.CollectionTabState
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CollectionListView(
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
            ResponseView(response = tab.games) {
                GameListView(
                    games = it,
                    listState = listState
                ) { game ->
                    GameRowView(
                        game = game,
                        onClick = { onRowClick(game) },
                        onStatusChange = { onStatusChange(game, it) }
                    )
                }
            }
        }
    }
}