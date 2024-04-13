package by.bsuir.gameslist.service.implementations

import android.os.Build
import androidx.annotation.RequiresApi
import by.bsuir.gameslist.dto.CollectionItemDto
import by.bsuir.gameslist.dto.GameDto
import by.bsuir.gameslist.dto.PlatformDto
import by.bsuir.gameslist.model.Game
import by.bsuir.gameslist.model.Platform
import by.bsuir.gameslist.model.Response
import by.bsuir.gameslist.model.Status
import by.bsuir.gameslist.repository.CollectionRepository
import by.bsuir.gameslist.repository.GameRepository
import by.bsuir.gameslist.repository.PlatformRepository
import by.bsuir.gameslist.service.GameService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class GameServiceImpl @Inject constructor(
    private val gameRepository: GameRepository,
    private val collectionRepository: CollectionRepository,
    private val platformRepository: PlatformRepository
) : GameService {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun getGames(userId: String): Flow<Response<List<Game>>> =
        combine(
            gameRepository.getGames(),
            collectionRepository.getCollectionItems(userId),
            platformRepository.getPlatforms()
        ) { gameDtos, collectionItems, platforms ->
            try {
                Response.Success(
                    gameDtos.map { gameDto ->
                        toModel(gameDto, platforms, collectionItems)
                    }
                )
            } catch (e: Exception) {
                Response.Error(e.message ?: "Unknown error")
            }
        }

    override suspend fun toggleStatus(gameId: String, status: Status, userId: String): Status? {
        val oldStatus = collectionRepository.getCollectionItem(userId, gameId)?.status
        val newStatus = if (oldStatus == status) null else status

        if (newStatus != null) {
            collectionRepository.addCollectionItem(
                userId,
                CollectionItemDto(gameId, status)
            )
        } else {
            collectionRepository.removeCollectionItem(userId, gameId)
        }

        return newStatus
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun toModel(
        game: GameDto,
        platforms: List<PlatformDto>,
        collectionItems: List<CollectionItemDto>
    ) = Game(
        id = game.id,
        title = game.title,
        developer = game.developer,
        publisher = game.publisher,
        releaseDate = LocalDateTime
            .parse(game.releaseDate, DateTimeFormatter.ofPattern("M/d/y h:m:s a z"))
            .toLocalDate(),
        genres = game.genres,
        platforms = game.platforms.map { platformId ->
            platforms
                .find { it.id == platformId }
                ?.let { Platform(it.name, it.abbreviation) }
                ?: throw IllegalArgumentException("Platform not found")
        },
        description = game.description,
        screenshots = game.screenshots,
        cover = game.cover,
        status = collectionItems.find { it.gameId == game.id }?.status
    )
}