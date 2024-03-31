package by.bsuir.gameslist.screens.authentication.views.signIn

data class SignInState(
    var validationError: String = "",
    var signInError: String = "",
    var isLoading: Boolean = false
)