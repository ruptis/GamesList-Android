package by.bsuir.gameslist.dto

import com.google.firebase.firestore.DocumentId

data class PlatformDto(
    @DocumentId var id: String = "",
    var name: String = "",
    var abbreviation: String = ""
)