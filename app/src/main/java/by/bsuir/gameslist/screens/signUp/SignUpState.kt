package by.bsuir.gameslist.screens.signUp

data class SignUpState(
    var validationError: String = "",
    var signUpError: String = "",
    var isLoading: Boolean = false
)