package by.bsuir.gameslist.screens.main.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import by.bsuir.gameslist.screens.main.MainScreenTab

@Composable
fun BottomBar(
    navController: NavController,
    currentDestination: NavDestination?
) {
    BottomBar(
        tabs = listOf(
            MainScreenTab.HOME,
            MainScreenTab.COLLECTION,
            MainScreenTab.PROFILE
        ),
        onTabClick = { tab ->
            navController.navigate(tab.route)
        },
        currentDestination = currentDestination
    )
}

@Composable
fun BottomBar(
    tabs: List<MainScreenTab>,
    onTabClick: (MainScreenTab) -> Unit,
    currentDestination: NavDestination?
) {
    NavigationBar {
        tabs.forEach { tab ->
            val selected = currentDestination.isSelected(tab.route)

            NavigationBarItem(
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
    }
}

private fun NavDestination?.isSelected(route: String): Boolean {
    return this?.hierarchy?.any { it.route?.contains(route) ?: false } ?: false
}