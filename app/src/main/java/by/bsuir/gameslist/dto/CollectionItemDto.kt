package by.bsuir.gameslist.dto

import by.bsuir.gameslist.model.Status
import com.google.firebase.firestore.DocumentId

data class CollectionItemDto(
    @DocumentId var gameId: String = "",
    var status: Status = Status.Playing,
)