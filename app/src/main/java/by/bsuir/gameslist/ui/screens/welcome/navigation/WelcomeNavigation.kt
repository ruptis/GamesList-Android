package by.bsuir.gameslist.ui.screens.welcome.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import by.bsuir.gameslist.ui.screens.welcome.WelcomeScreen

const val WELCOME_ROUTE = "welcome"

fun NavController.navigateToWelcome() = navigate(WELCOME_ROUTE)

fun NavGraphBuilder.welcomeScreen(
    onSignInMove: () -> Unit,
    onSignUpMove: () -> Unit
) {
    composable(WELCOME_ROUTE) {
        WelcomeScreen(
            onSignInMove = onSignInMove,
            onSignUpMove = onSignUpMove
        )
    }
}