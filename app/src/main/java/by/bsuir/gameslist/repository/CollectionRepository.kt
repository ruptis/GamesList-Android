package by.bsuir.gameslist.repository

import by.bsuir.gameslist.dto.CollectionItemDto
import kotlinx.coroutines.flow.Flow

interface CollectionRepository {
    fun getCollectionItems(userId: String): Flow<List<CollectionItemDto>>
    suspend fun getCollectionItem(userId: String, gameId: String): CollectionItemDto?
    suspend fun addCollectionItem(userId: String, collectionItem: CollectionItemDto)
    suspend fun removeCollectionItem(userId: String, gameId: String)
}