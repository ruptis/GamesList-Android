package by.bsuir.gameslist.service

import kotlinx.coroutines.flow.StateFlow

interface AuthenticationService {
    val isAuthenticated: StateFlow<Boolean>
    fun signIn(email: String, password: String)
    fun signUp(email: String, password: String)
    fun signOut()
}