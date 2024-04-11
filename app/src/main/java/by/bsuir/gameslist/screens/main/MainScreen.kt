package by.bsuir.gameslist.screens.main

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import by.bsuir.gameslist.app.navigation.MAIN_TABS_GRAPH
import by.bsuir.gameslist.app.navigation.mainTabsGraph
import by.bsuir.gameslist.screens.main.components.BottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    val topBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination
    val screenTitle =
        MainScreenTab.fromRoute(currentDestination?.route ?: MainScreenTab.HOME.route).title

    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController,
                currentDestination = currentDestination
            )
        },
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(screenTitle) },
                scrollBehavior = topBarScrollBehavior,
            )
        },
        modifier = modifier,
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp)
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = MAIN_TABS_GRAPH,
            modifier = modifier
                .padding(paddingValues)
                .consumeWindowInsets(paddingValues)
        ) {
            mainTabsGraph(navController, topBarScrollBehavior.nestedScrollConnection)
        }
    }
}

@Preview(name = "MainScreen")
@Composable
private fun PreviewMainScreen() {
    MainScreen()
}