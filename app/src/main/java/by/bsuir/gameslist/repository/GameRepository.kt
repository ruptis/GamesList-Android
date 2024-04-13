package by.bsuir.gameslist.repository

import by.bsuir.gameslist.dto.GameDto
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getGames() : Flow<List<GameDto>>
}