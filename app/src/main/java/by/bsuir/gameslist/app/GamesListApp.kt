package by.bsuir.gameslist.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import by.bsuir.gameslist.app.navigation.AUTHENTICATION_GRAPH
import by.bsuir.gameslist.app.navigation.authenticationGraph
import by.bsuir.gameslist.screens.main.navigation.MAIN_ROUTE
import by.bsuir.gameslist.screens.main.navigation.mainScreen

@Composable
fun GamesListApp(
    appViewModel: AppViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    val isAuthenticated by appViewModel.isAuthenticated.collectAsState()

    NavHost(
        navController,
        startDestination = if (isAuthenticated) MAIN_ROUTE else AUTHENTICATION_GRAPH
    ) {
        authenticationGraph(navController)
        mainScreen()
    }
}

@Composable
@Preview(name = "GamesListApp")
fun GamesListAppPreview() {
    GamesListApp()
}
