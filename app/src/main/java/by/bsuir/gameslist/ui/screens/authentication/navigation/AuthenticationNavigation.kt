package by.bsuir.gameslist.ui.screens.authentication.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import by.bsuir.gameslist.ui.screens.authentication.AuthenticationScreen
import by.bsuir.gameslist.ui.screens.authentication.AuthenticationState

const val AUTHENTICATION_ROUTE_BASE = "authentication"
const val STATE_ARG = "state"
const val AUTHENTICATION_ROUTE = "$AUTHENTICATION_ROUTE_BASE/{$STATE_ARG}"

fun NavController.navigateToSignIn() = navigateToAuthentication(AuthenticationState.SIGN_IN)

fun NavController.navigateToSignUp() = navigateToAuthentication(AuthenticationState.SIGN_UP)

fun NavController.navigateToAuthentication(state: AuthenticationState) =
    navigate("$AUTHENTICATION_ROUTE_BASE/${state.name}")

fun NavGraphBuilder.authenticationScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = AUTHENTICATION_ROUTE,
        arguments = listOf(
            navArgument(STATE_ARG) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val state = AuthenticationState.valueOf(
            backStackEntry.arguments?.getString(STATE_ARG) ?: AuthenticationState.SIGN_IN.name
        )

        AuthenticationScreen(
            initialState = state,
            onBackClick = onBackClick
        )
    }
}