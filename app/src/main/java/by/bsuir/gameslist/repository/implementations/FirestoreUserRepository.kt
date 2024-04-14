package by.bsuir.gameslist.repository.implementations

import by.bsuir.gameslist.dto.UserDto
import by.bsuir.gameslist.repository.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirestoreUserRepository @Inject constructor(
    firestore: FirebaseFirestore
) : UserRepository {
    private val _usersCollection = firestore.collection("users")

    override fun getUser(id: String): Flow<UserDto?> = _usersCollection.document(id).dataObjects()

    override suspend fun addUser(user: UserDto) {
        _usersCollection.add(user)
    }

    override suspend fun updateUser(user: UserDto) {
        _usersCollection.document(user.id).set(user)
    }
}