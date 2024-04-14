package by.bsuir.gameslist.repository.implementations

import by.bsuir.gameslist.dto.CollectionItemDto
import by.bsuir.gameslist.model.Status
import by.bsuir.gameslist.repository.CollectionRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreCollectionRepository @Inject constructor(
    firestore: FirebaseFirestore
) : CollectionRepository {
    private val _usersCollection = firestore.collection("users")

    override fun getCollectionItems(userId: String): Flow<List<CollectionItemDto>> =
        userCollection(userId).dataObjects()

    override fun getCollectionItems(status: Status, userId: String): Flow<List<CollectionItemDto>> =
        userCollection(userId).whereEqualTo("status", status).dataObjects()

    override suspend fun getCollectionItem(userId: String, gameId: String): CollectionItemDto? =
        userCollection(userId).document(gameId).get().await().toObject(CollectionItemDto::class.java)

    override fun getCollectionItemsCount(userId: String): Flow<Int> =
        getCollectionItems(userId).map { it.size }

    override fun getCollectionItemsCount(status: Status, userId: String): Flow<Int> =
        getCollectionItems(status, userId).map { it.size }

    override suspend fun addCollectionItem(userId: String, collectionItem: CollectionItemDto) {
        userCollection(userId).document(collectionItem.gameId).set(collectionItem)
    }

    override suspend fun removeCollectionItem(userId: String, gameId: String) {
        userCollection(userId).document(gameId).delete()
    }

    private fun userCollection(userId: String) =
        _usersCollection.document(userId).collection("games-collection")
}