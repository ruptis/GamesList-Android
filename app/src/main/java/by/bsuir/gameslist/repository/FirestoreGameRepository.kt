package by.bsuir.gameslist.repository

import by.bsuir.gameslist.model.Game
import by.bsuir.gameslist.model.Response
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirestoreGameRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) : GameRepository {
    private final val _collection = firestore.collection("games")

    override fun getGames(): Flow<Response<List<Game>>> = callbackFlow {
        val snapshotListener = _collection.addSnapshotListener { snapshot, exception ->
            val gamesResponse = if (exception != null) {
                Response.Error(exception)
            } else {
                val games = snapshot?.toObjects(Game::class.java) ?: emptyList()
                Response.Success(games)
            }
            trySend(gamesResponse)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun getGameById(id: String): Flow<Response<Game>> {
        TODO("Not yet implemented")
    }
}