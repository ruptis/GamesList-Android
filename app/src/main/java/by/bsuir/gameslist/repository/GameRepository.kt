package by.bsuir.gameslist.repository

import by.bsuir.gameslist.model.Game
import by.bsuir.gameslist.model.Response
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getGames() : Flow<Response<List<Game>>>
    fun getGameById(id: String) : Flow<Response<Game>>
}