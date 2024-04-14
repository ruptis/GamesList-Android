package by.bsuir.gameslist.ui.screens.profile

import by.bsuir.gameslist.model.User
import java.time.LocalDate

data class EditProfileState(
    val name: String = "",
    val country: String = "",
    val gender: User.Gender? = null,
    val dateOfBirth: LocalDate? = null,
    val bio: String = "",
    val links: String = ""
)