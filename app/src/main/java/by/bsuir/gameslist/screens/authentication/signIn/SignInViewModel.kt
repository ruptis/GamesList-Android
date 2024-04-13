package by.bsuir.gameslist.screens.authentication.signIn

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.bsuir.gameslist.model.Response
import by.bsuir.gameslist.service.AuthenticationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val _authenticationService: AuthenticationService
) : ViewModel() {
    private val _email: MutableStateFlow<String> = MutableStateFlow("")
    private val _password: MutableStateFlow<String> = MutableStateFlow("")
    private val _isValid: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _signInResponse: MutableStateFlow<Response<String>> =
        MutableStateFlow(Response.Success(""))

    val state: StateFlow<SignInState> = combine(
        _email,
        _password,
        _isValid,
        _signInResponse
    ) { email, password, isValid, signInResponse ->
        Log.d("SignInViewModel", "state: $email, $password, $isValid, $signInResponse")
        SignInState(
            email = email,
            password = password,
            isValid = isValid,
            signInResponse = signInResponse
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SignInState()
    )

    fun onEmailChange(email: String) {
        _email.value = email
        validate()
    }

    fun onPasswordChange(password: String) {
        _password.value = password
        validate()
    }

    fun signIn() {
        Log.d("SignInViewModel", "signIn: ")
        _signInResponse.value = Response.Loading
        viewModelScope.launch {
            _signInResponse.value = _authenticationService.signIn(_email.value, _password.value)
        }
    }

    private fun validate() {
        _isValid.value = validateEmail() && validatePassword()
    }
    private fun validatePassword(): Boolean = _password.value.length >= 6
    private fun validateEmail(): Boolean = Patterns.EMAIL_ADDRESS.matcher(_email.value).matches()
}
