package by.bsuir.gameslist.repository.implementations

import by.bsuir.gameslist.dto.GameDto
import by.bsuir.gameslist.repository.GameRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirestoreGameRepository @Inject constructor(
    firestore: FirebaseFirestore
) : GameRepository {
    private val _gamesCollection = firestore.collection("games")

    override fun getGames(): Flow<List<GameDto>> = _gamesCollection.dataObjects()
}