package by.bsuir.gameslist.repository

import by.bsuir.gameslist.model.CollectionItem
import by.bsuir.gameslist.model.Response
import kotlinx.coroutines.flow.Flow

interface CollectionRepository {
    fun getCollectionItems(userId: String): Flow<Response<List<CollectionItem>>>
}