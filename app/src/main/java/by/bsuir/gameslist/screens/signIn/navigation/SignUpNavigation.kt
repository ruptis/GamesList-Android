package by.bsuir.gameslist.screens.signIn.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import by.bsuir.gameslist.screens.signIn.SignInRoute

const val SIGN_IN_ROUTE = "signIn"

fun NavController.navigateToSignIn() = navigate(SIGN_IN_ROUTE) {
    popUpTo(SIGN_IN_ROUTE) { inclusive = true }
}

fun NavGraphBuilder.signInScreen(
    onSignUpMove: () -> Unit,
    onBackClick: () -> Unit
) {
    composable(SIGN_IN_ROUTE) {
        SignInRoute(
            onSignUpMove = onSignUpMove,
            onBackClick = onBackClick
        )
    }
}