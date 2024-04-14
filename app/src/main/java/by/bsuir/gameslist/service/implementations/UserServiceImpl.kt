package by.bsuir.gameslist.service.implementations

import android.os.Build
import androidx.annotation.RequiresApi
import by.bsuir.gameslist.dto.UserDto
import by.bsuir.gameslist.model.Response
import by.bsuir.gameslist.model.Status
import by.bsuir.gameslist.model.User
import by.bsuir.gameslist.repository.CollectionRepository
import by.bsuir.gameslist.repository.UserRepository
import by.bsuir.gameslist.service.UserService
import com.google.firebase.Timestamp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
class UserServiceImpl @Inject constructor(
    private val userRepository: UserRepository,
    private val collectionRepository: CollectionRepository
) : UserService {
    override fun getUser(id: String): Flow<Response<User>> = combine(
        userRepository.getUser(id),
        collectionRepository.getCollectionItemsCount(id),
        collectionRepository.getCollectionItemsCount(Status.Passed, id)
    ) { user, collectionItemsCount, passedItemsCount ->
        user?.let {
            try {
                Response.Success(
                    toModel(it, collectionItemsCount, passedItemsCount)
                )
            } catch (e: Exception) {
                Response.Error(e.message ?: "Unknown error")
            }
        } ?: Response.Error("User not found")
    }

    override suspend fun createUser(userId: String, name: String, email: String) {
        userRepository.addUser(
            UserDto(
                id = userId,
                name = name,
                email = email
            )
        )
    }

    override suspend fun updateUser(user: User) {
        userRepository.updateUser(
            toDto(user)
        )
    }

    private fun toModel(
        user: UserDto,
        collectionItemsCount: Int,
        passedItemsCount: Int
    ) = User(
        id = user.id,
        name = user.name,
        email = user.email,
        country = user.country,
        gender = user.gender,
        dateOfBirth = user.dateOfBirth?.toDate()?.toInstant()?.atZone(ZoneId.systemDefault())
            ?.toLocalDate(),
        dateOfRegistration = user.dateOfRegistration.toDate().toInstant()
            ?.atZone(ZoneId.systemDefault())
            ?.toLocalDate() ?: LocalDateTime.now().toLocalDate(),
        bio = user.bio,
        links = user.links,
        gamesCount = collectionItemsCount,
        passedGamesCount = passedItemsCount
    )

    private fun toDto(
        user: User
    ) = UserDto(
        id = user.id,
        name = user.name,
        email = user.email,
        country = user.country,
        gender = user.gender,
        dateOfBirth = Timestamp(
            Date.from(
                user.dateOfBirth?.atStartOfDay(ZoneId.systemDefault())?.toInstant()
            )
        ),
        dateOfRegistration = Timestamp(
            Date.from(
                user.dateOfRegistration.atStartOfDay(ZoneId.systemDefault()).toInstant()
            )
        ),
        bio = user.bio,
        links = user.links
    )
}