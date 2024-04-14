package by.bsuir.gameslist.ui.app.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import by.bsuir.gameslist.ui.screens.collection.navigation.collectionScreen
import by.bsuir.gameslist.ui.screens.home.navigation.HOME_ROUTE
import by.bsuir.gameslist.ui.screens.home.navigation.homeScreen
import by.bsuir.gameslist.ui.screens.profile.navigation.profileScreen

const val MAIN_TABS_GRAPH = "tabs"

fun NavGraphBuilder.mainTabsGraph() {
    navigation(
        startDestination = HOME_ROUTE,
        route = MAIN_TABS_GRAPH
    ) {
        homeScreen()
        collectionScreen()
        profileScreen()
    }
}