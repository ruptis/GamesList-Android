package by.bsuir.gameslist.screens.authentication.views.signIn

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import by.bsuir.gameslist.screens.authentication.views.components.AuthTextField
import by.bsuir.gameslist.ui.components.FilledTextButton

@Composable
fun SignInView(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val email by viewModel.email.collectAsState("")
    val password by viewModel.password.collectAsState("")

    SignInView(
        email = email,
        password = password,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onSignIn = viewModel::signIn,
        modifier = modifier
    )
}

@Composable
fun SignInView(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onSignIn: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
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
                .fillMaxWidth()
        )
    }
}

@Composable
@Preview(name = "SignIn")
private fun SignInViewPreview() {
    SignInView(
        email = "",
        password = "",
        onEmailChange = {},
        onPasswordChange = {},
        onSignIn = {}
    )
}

