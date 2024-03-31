package by.bsuir.gameslist.screens.signIn

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import by.bsuir.gameslist.screens.signUp.SignUpViewModel
import by.bsuir.gameslist.ui.components.AuthBottomButton
import by.bsuir.gameslist.ui.components.AuthTextField
import by.bsuir.gameslist.ui.components.AuthTopBar
import by.bsuir.gameslist.ui.components.FilledTextButton

@Composable
fun SignInRoute(
    onSignUpMove: () -> Unit,
    onBackClick: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val email by viewModel.email.collectAsState("")
    val password by viewModel.password.collectAsState("")

    SignInScreen(
        email = email,
        password = password,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onSignIn = viewModel::signIn,
        onSignUpMove = onSignUpMove,
        onBackClick = onBackClick
    )
}

@Composable
fun SignInScreen(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignIn: () -> Unit,
    onSignUpMove: () -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = { AuthTopBar("Sign In", onBackClick) },
        bottomBar = { AuthBottomButton("Don't have an account? Sign Up", onSignUpMove) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            AuthTextField("Email", email, onEmailChange)
            AuthTextField("Password", password, onPasswordChange)

            FilledTextButton(
                "Sign In",
                onClick = onSignIn,
                Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth())
        }
    }
}

@Composable
@Preview(name = "SignIn")
private fun SignInScreenPreview() {
    SignInScreen(
        email = "",
        password = "",
        onEmailChange = {},
        onPasswordChange = {},
        onSignIn = {},
        onSignUpMove = {},
        onBackClick = {}
    )
}

