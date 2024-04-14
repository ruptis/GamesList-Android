package by.bsuir.gameslist.repository

import by.bsuir.gameslist.dto.CollectionItemDto
import by.bsuir.gameslist.model.Status
import kotlinx.coroutines.flow.Flow

interface CollectionRepository {
    fun getCollectionItems(userId: String): Flow<List<CollectionItemDto>>
    fun getCollectionItems(status: Status, userId: String): Flow<List<CollectionItemDto>>
    suspend fun getCollectionItem(userId: String, gameId: String): CollectionItemDto?
    fun getCollectionItemsCount(userId: String): Flow<Int>
    fun getCollectionItemsCount(status: Status, userId: String): Flow<Int>
    suspend fun addCollectionItem(userId: String, collectionItem: CollectionItemDto)
    suspend fun removeCollectionItem(userId: String, gameId: String)
}