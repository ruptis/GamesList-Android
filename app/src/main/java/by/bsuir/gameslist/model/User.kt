package by.bsuir.gameslist.model

import java.time.LocalDate

data class User(
    var id: String,
    var name: String,
    var email: String,
    var country: String?,
    var gender: Gender?,
    var dateOfBirth: LocalDate?,
    val dateOfRegistration: LocalDate,
    var bio: String?,
    var links: String?,
    var gamesCount: Int,
    var passedGamesCount: Int
) {
    enum class Gender {
        Female,
        Male,
        Other
    }
}
