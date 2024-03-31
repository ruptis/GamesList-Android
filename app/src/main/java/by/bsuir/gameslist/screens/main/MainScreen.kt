package by.bsuir.gameslist.screens.main

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import by.bsuir.gameslist.screens.home.HomeScreen
import by.bsuir.gameslist.screens.home.mockGame

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    val topBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController
            )
        },
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Games") },
                scrollBehavior = topBarScrollBehavior
            )
        },
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = MainScreenTab.HOME.route,
            modifier = modifier
                .padding(paddingValues)
        ) {
            composable(MainScreenTab.HOME.route) {
                HomeScreen(games = (1..10).map { mockGame() }, modifier = Modifier.nestedScroll(topBarScrollBehavior.nestedScrollConnection))
            }
            composable(MainScreenTab.COLLECTION.route) {
                Box {
                    Text(MainScreenTab.COLLECTION.title)
                }
            }
            composable(MainScreenTab.PROFILE.route) {
                Box {
                    Text(MainScreenTab.PROFILE.title)
                }
            }

        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    BottomBar(
        tabs = listOf(
            MainScreenTab.HOME,
            MainScreenTab.COLLECTION,
            MainScreenTab.PROFILE
        ),
        navController = navController
    )
}

@Composable
fun BottomBar(
    tabs: List<MainScreenTab>,
    navController: NavHostController
) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    NavigationBar {
        tabs.forEach { tab ->
            val selected = currentDestination.isSelected(tab.route)
            Log.d("BottomBar", "selected: $selected")

            NavigationBarItem(
                selected = selected,
                onClick = { navController.navigate(tab.route) },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = if (selected) tab.selectedIcon else tab.icon),
                        contentDescription = tab.title
                    )
                },
                label = { Text(tab.title) }
            )
        }
    }
}

private fun NavDestination?.isSelected(route: String): Boolean {
    return this?.hierarchy?.any { it.route?.contains(route) ?: false } ?: false
}

@Preview(name = "MainScreen")
@Composable
private fun PreviewMainScreen() {
    MainScreen()
}