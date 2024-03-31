package by.bsuir.gameslist.service

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class MockAuthenticationService @Inject constructor() : AuthenticationService {
    private val _isAuthenticated: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val isAuthenticated: StateFlow<Boolean> = _isAuthenticated.asStateFlow()

    override fun signIn(email: String, password: String) {
        Log.d("AuthenticationService", "signIn: ")
        _isAuthenticated.value = true
    }

    override fun signUp(email: String, password: String) {
        _isAuthenticated.value = true
    }

    override fun signOut() {
        _isAuthenticated.value = false
    }
}