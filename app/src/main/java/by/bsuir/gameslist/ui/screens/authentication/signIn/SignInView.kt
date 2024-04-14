package by.bsuir.gameslist.ui.screens.authentication.signIn

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
fun SignInView(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()

    SignInView(
        email = state.email,
        password = state.password,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        isValid = state.isValid,
        response = state.signInResponse,
        onSignIn = viewModel::signIn,
        onSignInError = { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
            }
        },
        modifier = modifier
    )
}

@Composable
fun SignInView(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    isValid: Boolean,
    response: Response<String>,
    onSignIn: () -> Unit,
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
                onTextChanged = onEmailChange,
                type = TextFieldType.Email
            )

            AuthTextField(
                title = "Password",
                text = password,
                onTextChanged = onPasswordChange,
                type = TextFieldType.Password
            )

            FilledTextButton(
                "Sign In",
                onClick = onSignIn,
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
@Preview(name = "SignIn")
private fun SignInViewPreview() {
    SignInView(
        email = "",
        password = "",
        onEmailChange = {},
        onPasswordChange = {},
        isValid = true,
        response = Response.Loading,
        onSignIn = {},
        onSignInError = {}
    )
}

