package by.bsuir.gameslist.screens.authentication.views.signUp

data class SignUpState(
    var validationError: String = "",
    var signUpError: String = "",
    var isLoading: Boolean = false
)