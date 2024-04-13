package by.bsuir.gameslist.dto

import by.bsuir.gameslist.model.User
import com.google.firebase.firestore.DocumentId
import com.google.type.Date

data class UserDto(
    @DocumentId var id: String,
    var name: String,
    var email: String,
    var country: String?,
    var gender: User.Gender?,
    var dateOfBirth: Date?,
    val dateOfRegistration: Date,
    var bio: String?,
    var links: List<String>?
)
