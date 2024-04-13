package by.bsuir.gameslist.dto

import com.google.firebase.firestore.DocumentId

data class GameDto(
    @DocumentId var id: String = "",
    var title: String = "",
    var developer: String = "",
    var publisher: String = "",
    var releaseDate: String = "",
    var genres: List<String> = listOf(),
    var platforms: List<String> = listOf(),
    var description: String = "",
    var screenshots: List<String> = listOf(),
    var cover: String = "",
)
