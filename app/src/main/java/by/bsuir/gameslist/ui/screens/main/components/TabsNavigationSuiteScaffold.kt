package by.bsuir.gameslist.ui.screens.main.components

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.ExperimentalMaterial3AdaptiveNavigationSuiteApi
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.navOptions
import by.bsuir.gameslist.ui.screens.main.MainScreenTab

@Composable
fun TabsNavigationSuiteScaffold(
    navController: NavController,
    currentDestination: NavDestination?,
    content: @Composable () -> Unit
) {
    TabsNavigationSuiteScaffold(
        tabs = listOf(
            MainScreenTab.HOME,
            MainScreenTab.COLLECTION,
            MainScreenTab.PROFILE
        ),
        onTabClick = { tab ->
            navController.navigate(
                route = tab.route,
                navOptions = navOptions {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                })
        },
        currentDestination = currentDestination
    ) {
        content()
    }
}

@OptIn(ExperimentalMaterial3AdaptiveNavigationSuiteApi::class)
@Composable
fun TabsNavigationSuiteScaffold(
    tabs: List<MainScreenTab>,
    onTabClick: (MainScreenTab) -> Unit,
    currentDestination: NavDestination?,
    content: @Composable () -> Unit
) {
    NavigationSuiteScaffold(navigationSuiteItems = {
        tabs.forEach { tab ->
            val selected = currentDestination.isSelected(tab.route)

            item(
                selected = selected,
                onClick = { onTabClick(tab) },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = if (selected) tab.selectedIcon else tab.icon),
                        contentDescription = tab.title
                    )
                },
                label = { Text(tab.title) }
            )
        }
    }) {
        content()
    }
}

private fun NavDestination?.isSelected(route: String): Boolean {
    return this?.hierarchy?.any { it.route?.contains(route) ?: false } ?: false
}