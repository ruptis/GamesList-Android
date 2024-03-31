package by.bsuir.gameslist.app.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import by.bsuir.gameslist.screens.signIn.navigation.navigateToSignIn
import by.bsuir.gameslist.screens.signIn.navigation.signInScreen
import by.bsuir.gameslist.screens.signUp.navigation.navigateToSignUp
import by.bsuir.gameslist.screens.signUp.navigation.signUpScreen
import by.bsuir.gameslist.screens.welcome.navigation.WELCOME_ROUTE
import by.bsuir.gameslist.screens.welcome.navigation.welcomeScreen

const val AUTHENTICATION_GRAPH = "authentication"

fun NavGraphBuilder.authenticationGraph(navController: NavController) {
    navigation(
        startDestination = WELCOME_ROUTE,
        route = AUTHENTICATION_GRAPH
    ) {
        welcomeScreen(
            onSignInMove = { navController.navigateToSignIn() },
            onSignUpMove = { navController.navigateToSignUp() }
        )
        signInScreen(
            onSignUpMove = { navController.navigateToSignUp() },
            onBackClick = { navController.popBackStack() }
        )
        signUpScreen(
            onSignInMove = { navController.navigateToSignIn() },
            onBackClick = { navController.popBackStack() }
        )
    }

}