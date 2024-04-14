package by.bsuir.gameslist.ui.screens.authentication.signUp

import by.bsuir.gameslist.model.Response

data class SignUpState(
    var email: String = "",
    var name: String = "",
    var password: String = "",
    var confirmPassword: String = "",
    var isValid: Boolean = false,
    var signUpResponse: Response<String> = Response.Success("")
)