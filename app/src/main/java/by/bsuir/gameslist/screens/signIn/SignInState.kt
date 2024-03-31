package by.bsuir.gameslist.screens.signIn

data class SignInState(
    var validationError: String = "",
    var signInError: String = "",
    var isLoading: Boolean = false
)