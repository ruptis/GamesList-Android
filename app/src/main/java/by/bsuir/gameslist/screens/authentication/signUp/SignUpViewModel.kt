package by.bsuir.gameslist.screens.authentication.signUp

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
class SignUpViewModel @Inject constructor(
    private val authenticationService: AuthenticationService
) : ViewModel() {
    private val _email: MutableStateFlow<String> = MutableStateFlow("")
    private val _name: MutableStateFlow<String> = MutableStateFlow("")
    private val _password: MutableStateFlow<String> = MutableStateFlow("")
    private val _confirmPassword: MutableStateFlow<String> = MutableStateFlow("")
    private val _isValid: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _signUpResponse: MutableStateFlow<Response<String>> =
        MutableStateFlow(Response.Success(""))

    val state: StateFlow<SignUpState> = combine(
        _email,
        _name,
        _password,
        _confirmPassword,
        _isValid,
        _signUpResponse
    ) { args: Array<Any> ->
        @Suppress("UNCHECKED_CAST")
        (SignUpState(
        email = args[0] as String,
        name = args[1] as String,
        password = args[2] as String,
        confirmPassword = args[3] as String,
        isValid = args[4] as Boolean,
        signUpResponse = args[5] as Response<String>
    ))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SignUpState()
    )

    fun onEmailChange(email: String) {
        _email.value = email
        validate()
    }

    fun onNameChange(name: String) {
        _name.value = name
        validate()
    }

    fun onPasswordChange(password: String) {
        _password.value = password
        validate()
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        _confirmPassword.value = confirmPassword
        validate()
    }

    fun signUp() {
        Log.d("SignUpViewModel", "signIn: ${_email.value}, ${_password.value}")
        viewModelScope.launch {
            _signUpResponse.value =
                authenticationService.signUp(_email.value, _name.value, _password.value)
        }
    }

    private fun validate() {
        _isValid.value = validateEmail() && validateName() && validatePassword() && validateConfirmPassword()
    }

    private fun validateEmail(): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(_email.value).matches()

    private fun validateName(): Boolean =
        _name.value.isNotEmpty()

    private fun validatePassword(): Boolean =
        _password.value.length >= 6

    private fun validateConfirmPassword(): Boolean =
        _confirmPassword.value == _password.value
}