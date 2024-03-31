package by.bsuir.gameslist.screens.authentication

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import by.bsuir.gameslist.screens.authentication.views.signIn.SignInView
import by.bsuir.gameslist.screens.authentication.views.signUp.SignUpView
import by.bsuir.gameslist.ui.components.AuthBottomButton
import by.bsuir.gameslist.ui.components.AuthTopBar

@Composable
fun AuthenticationScreen(
    initialState: AuthenticationState,
    onBackClick: () -> Unit
) {
    var authState by rememberSaveable { mutableStateOf(initialState) }

    Scaffold(
        topBar = { AuthTopBar(authState.title, onBackClick) },
        bottomBar = { AuthBottomButton(authState.bottomText) { authState = authState.switch() } }
    ) { paddingValues ->
        when (authState) {
            AuthenticationState.SIGN_IN -> SignInView(modifier = Modifier.fillMaxSize().padding(paddingValues))
            AuthenticationState.SIGN_UP -> SignUpView(modifier = Modifier.fillMaxSize().padding(paddingValues))
        }
    }
}

@Composable
@Preview(name = "AuthenticationScreen")
fun AuthenticationScreenPreview() {
    AuthenticationScreen(
        initialState = AuthenticationState.SIGN_IN,
        onBackClick = {}
    )
}