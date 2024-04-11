package by.bsuir.gameslist.app.navigation

import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import by.bsuir.gameslist.screens.collection.navigation.collectionScreen
import by.bsuir.gameslist.screens.details.navigation.detailsScreen
import by.bsuir.gameslist.screens.details.navigation.navigateToDetails
import by.bsuir.gameslist.screens.home.navigation.HOME_ROUTE
import by.bsuir.gameslist.screens.home.navigation.homeScreen

const val MAIN_TABS_GRAPH = "tabs"

fun NavGraphBuilder.mainTabsGraph(
    navController: NavController,
    nestedScrollConnection: NestedScrollConnection
) {
    navigation(
        startDestination = HOME_ROUTE,
        route = MAIN_TABS_GRAPH
    ) {
        homeScreen(
            onGameCardClick = { id -> navController.navigateToDetails(id) },
            nestedScrollConnection = nestedScrollConnection
        )
        collectionScreen(nestedScrollConnection)
        detailsScreen()
    }
}