package by.bsuir.gameslist.repository

import by.bsuir.gameslist.dto.PlatformDto
import by.bsuir.gameslist.model.Platform
import kotlinx.coroutines.flow.Flow

interface PlatformRepository {
    fun getPlatforms(): Flow<List<PlatformDto>>
}