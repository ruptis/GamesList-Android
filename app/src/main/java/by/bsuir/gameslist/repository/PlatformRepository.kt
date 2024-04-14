package by.bsuir.gameslist.repository

import by.bsuir.gameslist.dto.PlatformDto
import kotlinx.coroutines.flow.Flow

interface PlatformRepository {
    fun getPlatforms(): Flow<List<PlatformDto>>
}