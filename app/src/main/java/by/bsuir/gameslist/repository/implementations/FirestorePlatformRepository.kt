package by.bsuir.gameslist.repository.implementations

import by.bsuir.gameslist.dto.PlatformDto
import by.bsuir.gameslist.repository.PlatformRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirestorePlatformRepository @Inject constructor(
    firestore: FirebaseFirestore
) : PlatformRepository {
    private val _platformsCollection = firestore.collection("platforms")
    override fun getPlatforms(): Flow<List<PlatformDto>> = _platformsCollection.dataObjects()
}