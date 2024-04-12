package by.bsuir.gameslist.screens.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import by.bsuir.gameslist.screens.home.HomeScreen

const val HOME_ROUTE = "home"

fun NavController.navigateToHome() = navigate(HOME_ROUTE)

fun NavGraphBuilder.homeScreen(onGameCardClick: (String) -> Unit) {
    composable(route = HOME_ROUTE) {
        HomeScreen(
            onGameCardClick = { game -> onGameCardClick(game.id) }
        )
    }
}