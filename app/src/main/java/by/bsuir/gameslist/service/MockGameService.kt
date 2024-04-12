package by.bsuir.gameslist.service

import by.bsuir.gameslist.model.Game
import by.bsuir.gameslist.model.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MockGameService @Inject constructor(
    private val data: MockData
) : GameService {
    override fun getGames(): Flow<Response<List<Game>>> {
        return data.games.let { games ->
            flow {
                emit(Response.Success(games))
            }
        }
    }

    override suspend fun toggleStatus(gameId: String, status: Game.Status): Game {
        val game = data.games.find { it.id == gameId } ?: throw IllegalArgumentException("Game not found")
        val gameStatus = game.status
        val newStatus = if (gameStatus == null || gameStatus != status) status else null
        val newGame = game.copy(status = newStatus)
        data.games = data.games.map { if (it.id == gameId) newGame else it }
        return newGame
    }
}