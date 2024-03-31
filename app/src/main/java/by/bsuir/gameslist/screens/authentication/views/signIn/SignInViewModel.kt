package by.bsuir.gameslist.screens.authentication.views.signIn

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import by.bsuir.gameslist.service.AuthenticationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val _authenticationService: AuthenticationService
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<SignInState> = MutableStateFlow(SignInState())
    val stateFlow: StateFlow<SignInState> = _stateFlow.asStateFlow()

    private val _email: MutableStateFlow<String> = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password: MutableStateFlow<String> = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    fun onEmailChange(email: String) {
        _email.value = email
    }

    fun onPasswordChange(password: String) {
        _password.value = password
    }

    fun signIn() {
        Log.d("SignInViewModel", "signIn: ")
        val email = _email.value
        val password = _password.value
        _authenticationService.signIn(email, password)
    }
    private fun validateEmail(email: String): Boolean =  Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun validatePassword(password: String): Boolean = password.length >= 6

    fun validateSignIn(email: String, password: String): Boolean =
        validateEmail(email) && validatePassword(password)
}