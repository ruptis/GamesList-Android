package by.bsuir.gameslist.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

data class Game(
    var id: String,
    var title: String,
    var developer: String,
    var publisher: String,
    var releaseDate: LocalDate,
    var genres: List<String>,
    var platforms: List<Platform>,
    var description: String,
    var screenshots: List<String>,
    var cover: String,
    var status: Status?
) {
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun mockGame(id: String = "0", status: Status? = null): Game {
            return Game(
                id = id,
                title = "Game Title",
                releaseDate = LocalDate.now(),
                description = "desc",
                genres = listOf("Action", "Adventure"),
                developer = "Developer",
                publisher = "Publisher",
                screenshots = listOf("", "", "", "", "", "", ""),
                cover = "",
                platforms = listOf(
                    Platform( "PC", "PC"),
                    Platform("PlayStation 5", "PS5")
                ),
                status = status
            )
        }
    }
}

