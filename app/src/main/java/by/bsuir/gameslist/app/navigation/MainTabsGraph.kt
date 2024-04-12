package by.bsuir.gameslist.app.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import by.bsuir.gameslist.screens.collection.navigation.collectionScreen
import by.bsuir.gameslist.screens.details.navigation.detailsScreen
import by.bsuir.gameslist.screens.details.navigation.navigateToDetails
import by.bsuir.gameslist.screens.home.navigation.HOME_ROUTE
import by.bsuir.gameslist.screens.home.navigation.homeScreen
import by.bsuir.gameslist.screens.profile.navigation.profileScreen

const val MAIN_TABS_GRAPH = "tabs"

fun NavGraphBuilder.mainTabsGraph(navController: NavController) {
    navigation(
        startDestination = HOME_ROUTE,
        route = MAIN_TABS_GRAPH
    ) {
        homeScreen(
            onGameCardClick = { id -> navController.navigateToDetails(id) },
        )
        collectionScreen()
        profileScreen()
        detailsScreen()
    }
}