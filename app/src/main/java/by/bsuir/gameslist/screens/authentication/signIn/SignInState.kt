package by.bsuir.gameslist.screens.authentication.signIn

import by.bsuir.gameslist.model.Response

data class SignInState(
    var email: String = "",
    var password: String = "",
    var isValid: Boolean = false,
    var signInResponse: Response<String> = Response.Success("")
)