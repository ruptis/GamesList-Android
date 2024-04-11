package by.bsuir.gameslist.screens.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import by.bsuir.gameslist.screens.main.MainScreen

const val MAIN_ROUTE = "main"

fun NavGraphBuilder.mainScreen() {
    composable(route = MAIN_ROUTE) {
        MainScreen()
    }
}