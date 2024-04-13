package by.bsuir.gameslist.service

import by.bsuir.gameslist.model.Game
import by.bsuir.gameslist.model.Response
import by.bsuir.gameslist.model.Status
import kotlinx.coroutines.flow.Flow

interface GameService {
    fun getGames(userId: String): Flow<Response<List<Game>>>
    suspend fun toggleStatus(gameId: String, status: Status, userId: String): Status?
}