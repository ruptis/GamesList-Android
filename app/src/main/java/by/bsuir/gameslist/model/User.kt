package by.bsuir.gameslist.model

import com.google.type.Date

data class User(
    var id: String,
    var name: String,
    var email: String,
    var country: String?,
    var gender: Gender?,
    var dateOfBirth: Date?,
    val dateOfRegistration: Date,
    var bio: String?,
    var links: List<String>?,
    var gamesCount: Int,
    var passedGamesCount: Int
) {
    enum class Gender {
        FEMALE,
        MALE,
        OTHER
    }
}
