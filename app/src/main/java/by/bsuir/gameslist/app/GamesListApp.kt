package by.bsuir.gameslist.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import by.bsuir.gameslist.app.navigation.AUTHENTICATION_GRAPH
import by.bsuir.gameslist.app.navigation.authenticationGraph

@Composable
fun GamesListApp() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = AUTHENTICATION_GRAPH) {
        authenticationGraph(navController)
    }
}

@Composable
@Preview(name = "GamesListApp")
fun GamesListAppPreview() {
    GamesListApp()
}