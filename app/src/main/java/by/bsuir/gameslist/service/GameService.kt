package by.bsuir.gameslist.service

import by.bsuir.gameslist.model.Game
import by.bsuir.gameslist.model.Response
import kotlinx.coroutines.flow.Flow

interface GameService {
    fun getGames(): Flow<Response<List<Game>>>
    suspend fun toggleStatus(gameId: String, status: Game.Status): Game
}