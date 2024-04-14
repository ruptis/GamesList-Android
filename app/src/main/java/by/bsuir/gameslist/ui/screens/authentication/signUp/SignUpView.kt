package by.bsuir.gameslist.ui.screens.authentication.signUp

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import by.bsuir.gameslist.model.Response
import by.bsuir.gameslist.ui.components.FilledTextButton
import by.bsuir.gameslist.ui.screens.authentication.components.AuthTextField
import by.bsuir.gameslist.ui.screens.authentication.components.TextFieldType
import kotlinx.coroutines.launch

@Composable
fun SignUpView(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()

    SignUpView(
        email = state.email,
        name = state.name,
        password = state.password,
        confirmPassword = state.confirmPassword,
        onEmailChanged = viewModel::onEmailChange,
        onNameChanged = viewModel::onNameChange,
        onPasswordChanged = viewModel::onPasswordChange,
        onConfirmPasswordChanged = viewModel::onConfirmPasswordChange,
        isValid = state.isValid,
        response = state.signUpResponse,
        onSignUp = viewModel::signUp,
        onSignInError = { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
            }
        },
        modifier = modifier
    )
}

@Composable
private fun SignUpView(
    email: String,
    onEmailChanged: (String) -> Unit,
    name: String,
    onNameChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    confirmPassword: String,
    onConfirmPasswordChanged: (String) -> Unit,
    isValid: Boolean,
    response: Response<String>,
    onSignUp: () -> Unit,
    onSignInError: (String) -> Unit,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState()
) {
    Box {
        Column(
            modifier = modifier
                .padding(48.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            AuthTextField(
                title = "Email",
                text = email,
                onTextChanged = onEmailChanged,
                type = TextFieldType.Email
            )
            AuthTextField(
                title = "Name",
                text = name,
                onTextChanged = onNameChanged
            )
            AuthTextField(
                title = "Password",
                text = password,
                onTextChanged = onPasswordChanged,
                type = TextFieldType.Password
            )
            AuthTextField(
                title = "Confirm Password",
                text = confirmPassword,
                onTextChanged = onConfirmPasswordChanged,
                type = TextFieldType.Password
            )

            FilledTextButton(
                "Sign Up",
                onClick = onSignUp,
                enabled = isValid,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
            )
        }

        when (response) {
            is Response.Error -> onSignInError(response.message)
            is Response.Success -> Unit
            is Response.Loading -> CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
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
        onEmailChanged = {},
        onNameChanged = {},
        onPasswordChanged = {},
        onConfirmPasswordChanged = {},
        isValid = true,
        response = Response.Loading,
        onSignUp = {},
        onSignInError = {}
    )
}

