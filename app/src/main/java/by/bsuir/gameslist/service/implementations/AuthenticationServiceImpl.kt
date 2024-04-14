package by.bsuir.gameslist.service.implementations

import by.bsuir.gameslist.model.Response
import by.bsuir.gameslist.service.AuthenticationService
import by.bsuir.gameslist.service.UserService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationServiceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val userService: UserService
) : AuthenticationService {
    override fun observeUserId(): Flow<String?> = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser?.uid)
        }
        firebaseAuth.addAuthStateListener(authStateListener)
        awaitClose { firebaseAuth.removeAuthStateListener(authStateListener) }
    }

    override fun getUserId(): String? = firebaseAuth.currentUser?.uid

    override suspend fun signIn(email: String, password: String): Response<String> =
        try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            result.user?.uid?.let { Response.Success(it) } ?: Response.Error("User not found")
        } catch (e: Exception) {
            Response.Error(e.message ?: "Unknown error")
        }

    override suspend fun signUp(email: String, name: String, password: String): Response<String> =
        try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            result.user?.uid?.let {
                userService.createUser(it, name, email)
                Response.Success(it)
            } ?: throw Exception("User not found")
        } catch (e: Exception) {
            firebaseAuth.currentUser?.delete()
            Response.Error(e.message ?: "Unknown error")
        }


    override fun signOut() {
        firebaseAuth.signOut()
    }
}