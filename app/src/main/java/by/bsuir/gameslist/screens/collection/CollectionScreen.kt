package by.bsuir.gameslist.screens.collection

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import by.bsuir.gameslist.model.Game
import by.bsuir.gameslist.screens.home.HomeScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CollectionScreen(
    modifier: Modifier = Modifier
) {
    val games by remember {
        mutableStateOf((1..10).map { Game.mockGame(it.toString(), Game.Status.entries.random()) })
    }
    val tabs = CollectionTab.entries
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val scope = rememberCoroutineScope()

    Column(modifier = modifier.fillMaxSize()) {
        SecondaryTabRow(
            selectedTabIndex = pagerState.currentPage, modifier = modifier
        ) {
            tabs.forEachIndexed { index, tab ->
                val selected = pagerState.currentPage == index
                Tab(icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = if (selected) tab.selectedIcon else tab.icon),
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
                .fillMaxSize()
        ) { page ->
            val tab = tabs[page]
            HomeScreen(
                games = games.filter { it.status == tab.status },
                onCardClick = {},
                onStatusChange = { _, _ -> },
                modifier = modifier
            )
        }
    }
}

@Preview(name = "CollectionScreen", showBackground = true)
@Composable
private fun PreviewCollectionScreen() {
    CollectionScreen()
}