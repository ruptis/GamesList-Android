package by.bsuir.gameslist.screens.authentication

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import by.bsuir.gameslist.screens.authentication.components.AuthBottomButton
import by.bsuir.gameslist.screens.authentication.components.AuthTopBar
import by.bsuir.gameslist.screens.authentication.signIn.SignInView
import by.bsuir.gameslist.screens.authentication.signUp.SignUpView

@Composable
fun AuthenticationScreen(
    initialState: AuthenticationState,
    onBackClick: () -> Unit
) {
    var authState by rememberSaveable { mutableStateOf(initialState) }
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) {
                Snackbar(
                    snackbarData = it,
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onError
                )
            }
        },
        topBar = { AuthTopBar(authState.title, onBackClick) },
        bottomBar = { AuthBottomButton(authState.bottomText) { authState = authState.switch() } }
    ) { paddingValues ->
        when (authState) {
            AuthenticationState.SIGN_IN -> SignInView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                snackbarHostState = snackbarHostState
            )

            AuthenticationState.SIGN_UP -> SignUpView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                snackbarHostState = snackbarHostState
            )
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