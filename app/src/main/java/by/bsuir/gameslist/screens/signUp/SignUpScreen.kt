package by.bsuir.gameslist.screens.signUp

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
import by.bsuir.gameslist.ui.components.AuthBottomButton
import by.bsuir.gameslist.ui.components.AuthTextField
import by.bsuir.gameslist.ui.components.AuthTopBar
import by.bsuir.gameslist.ui.components.FilledTextButton

@Composable
fun SignUpRoute(
    onSignInMove: () -> Unit,
    onBackClick: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val email by viewModel.email.collectAsState("")
    val name by viewModel.name.collectAsState("")
    val password by viewModel.password.collectAsState("")
    val confirmPassword by viewModel.confirmPassword.collectAsState("")

    SignUpScreen(
        email = email,
        name = name,
        password = password,
        confirmPassword = confirmPassword,
        onEmailChange = viewModel::onEmailChange,
        onNameChange = viewModel::onNameChange,
        onPasswordChange = viewModel::onPasswordChange,
        onConfirmPasswordChange = viewModel::onConfirmPasswordChange,
        onSignUp = viewModel::signIn,
        onSignInMove = onSignInMove,
        onBackClick = onBackClick
    )
}

@Composable
fun SignUpScreen(
    email: String,
    name: String,
    password: String,
    confirmPassword: String,
    onEmailChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onSignUp: () -> Unit,
    onSignInMove: () -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = { AuthTopBar("Sign Up", onBackClick) },
        bottomBar = { AuthBottomButton("Already have an account? Sign In", onSignInMove) }
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
            AuthTextField("Name", name, onNameChange)
            AuthTextField("Password", password, onPasswordChange)
            AuthTextField("Confirm Password", confirmPassword, onConfirmPasswordChange)

            FilledTextButton(
                "Sign In",
                onClick = onSignUp,
                Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth())
        }
    }
}

@Composable
@Preview(name = "SignUp")
private fun SignUpScreenPreview() {
    SignUpScreen(
        email = "",
        name = "",
        password = "",
        confirmPassword = "",
        onEmailChange = {},
        onNameChange = {},
        onPasswordChange = {},
        onConfirmPasswordChange = {},
        onSignUp = {},
        onSignInMove = {},
        onBackClick = {}
    )
}

