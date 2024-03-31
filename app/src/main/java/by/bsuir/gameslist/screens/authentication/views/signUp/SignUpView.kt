package by.bsuir.gameslist.screens.authentication.views.signUp

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
fun SignUpView(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val email by viewModel.email.collectAsState("")
    val name by viewModel.name.collectAsState("")
    val password by viewModel.password.collectAsState("")
    val confirmPassword by viewModel.confirmPassword.collectAsState("")

    SignUpView(
        email = email,
        name = name,
        password = password,
        confirmPassword = confirmPassword,
        onEmailChange = viewModel::onEmailChange,
        onNameChange = viewModel::onNameChange,
        onPasswordChange = viewModel::onPasswordChange,
        onConfirmPasswordChange = viewModel::onConfirmPasswordChange,
        onSignUp = viewModel::signIn,
        modifier = modifier
    )
}

@Composable
private fun SignUpView(
    email: String,
    onEmailChange: (String) -> Unit,
    name: String,
    onNameChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    confirmPassword: String,
    onConfirmPasswordChange: (String) -> Unit,
    onSignUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        AuthTextField("Email", email, onEmailChange)
        AuthTextField("Name", name, onNameChange)
        AuthTextField("Password", password, onPasswordChange)
        AuthTextField("Confirm Password", confirmPassword, onConfirmPasswordChange)

        FilledTextButton(
            "Sign Up",
            onClick = onSignUp,
            Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
@Preview(name = "SignUp")
private fun SignUpViewPreview() {
    SignUpView(
        email = "",
        name = "",
        password = "",
        confirmPassword = "",
        onEmailChange = {},
        onNameChange = {},
        onPasswordChange = {},
        onConfirmPasswordChange = {},
        onSignUp = {}
    )
}

