package by.bsuir.gameslist.screens.signUp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import by.bsuir.gameslist.screens.signUp.SignUpRoute

const val SIGN_UP_ROUTE = "signUp"

fun NavController.navigateToSignUp() = navigate(SIGN_UP_ROUTE) {
    popUpTo(SIGN_UP_ROUTE) { inclusive = true }
}

fun NavGraphBuilder.signUpScreen(
    onSignInMove: () -> Unit,
    onBackClick: () -> Unit
) {
    composable(SIGN_UP_ROUTE) {
        SignUpRoute(
            onSignInMove = onSignInMove,
            onBackClick = onBackClick
        )
    }
}