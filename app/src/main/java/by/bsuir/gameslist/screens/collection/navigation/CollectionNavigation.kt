package by.bsuir.gameslist.screens.collection.navigation

import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import by.bsuir.gameslist.screens.collection.CollectionScreen

const val COLLECTION_ROUTE = "collection"

fun NavController.navigateToCollection() = navigate(COLLECTION_ROUTE)

fun NavGraphBuilder.collectionScreen(nestedScrollConnection: NestedScrollConnection) {
    composable(route = COLLECTION_ROUTE) {
        CollectionScreen(nestedScrollConnection = nestedScrollConnection)
    }
}