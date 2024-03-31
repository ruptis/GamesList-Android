package by.bsuir.gameslist.screens.authentication

enum class AuthenticationState(val title: String, val bottomText: String) {
    SIGN_IN("Sign In", "Don't have an account? Sign Up"),
    SIGN_UP("Sign Up", "Already have an account? Sign In");

    fun switch(): AuthenticationState = when (this) {
        SIGN_IN -> SIGN_UP
        SIGN_UP -> SIGN_IN
    }
}