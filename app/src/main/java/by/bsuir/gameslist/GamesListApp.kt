package by.bsuir.gameslist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun GamesListApp() {
    WelcomeView(
        onSignIn = {},
        onSignUp = {},
        modifier = Modifier.fillMaxSize()
    )
}
