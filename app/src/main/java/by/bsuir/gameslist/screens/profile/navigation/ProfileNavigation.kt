package by.bsuir.gameslist.screens.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import by.bsuir.gameslist.screens.profile.ProfileScreen

const val PROFILE_ROUTE = "profile"

fun NavController.navigateToProfile() = navigate(PROFILE_ROUTE)

fun NavGraphBuilder.profileScreen() {
    composable(route = PROFILE_ROUTE) {
        ProfileScreen()
    }
}