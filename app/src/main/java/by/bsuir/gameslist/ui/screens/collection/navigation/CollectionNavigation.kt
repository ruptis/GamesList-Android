package by.bsuir.gameslist.ui.screens.collection.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import by.bsuir.gameslist.ui.screens.collection.CollectionScreen

const val COLLECTION_ROUTE = "collection"

fun NavController.navigateToCollection() = navigate(COLLECTION_ROUTE)

fun NavGraphBuilder.collectionScreen() {
    composable(route = COLLECTION_ROUTE) {
        CollectionScreen()
    }
}