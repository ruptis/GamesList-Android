package by.bsuir.gameslist.screens.details.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import by.bsuir.gameslist.screens.details.DetailsScreen

const val DETAILS_ROUTE_BASE = "details"
const val ID_ARG = "id"
const val DETAILS_ROUTE = "$DETAILS_ROUTE_BASE/{$ID_ARG}"

fun NavController.navigateToDetails(id: String) = navigate("$DETAILS_ROUTE_BASE/$id")

fun NavGraphBuilder.detailsScreen() {
    composable(
        route = DETAILS_ROUTE,
        arguments = listOf(
            navArgument(ID_ARG) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val id = backStackEntry.arguments?.getString(ID_ARG) ?: ""

        DetailsScreen(
            id = id,
        )
    }
}