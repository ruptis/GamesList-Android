package by.bsuir.gameslist.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import by.bsuir.gameslist.app.navigation.AUTHENTICATION_GRAPH
import by.bsuir.gameslist.app.navigation.authenticationGraph
import by.bsuir.gameslist.screens.main.MainScreen

@Composable
fun GamesListApp(
    appViewModel: AppViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    val isAuthenticated by appViewModel.isAuthenticated.collectAsState()

    NavHost(navController, startDestination = if (isAuthenticated) "main" else AUTHENTICATION_GRAPH) {
        authenticationGraph(navController)
        navigation(route = "main", startDestination = "mains") {
            composable("mains") {
                MainScreen()
            }
        }
    }
}

@Composable
@Preview(name = "GamesListApp")
fun GamesListAppPreview() {
    GamesListApp()
}
