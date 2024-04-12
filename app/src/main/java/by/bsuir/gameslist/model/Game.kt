package by.bsuir.gameslist.model

data class Game(
    var id: String,
    var title: String,
    var releaseDate: String,
    var description: String,
    var platforms: List<String>,
    var genres: List<String>,
    var developer: String,
    var publisher: String,
    var screenshots: List<String>,
    var cover: String,
    var platformsData: List<Platform>? = null,
    var status: Status? = null
) {
    enum class Status {
        PLAYING,
        PLANNING,
        PASSED,
        ABANDONED
    }

    companion object {
        fun mockGame(id: String = "0", status: Status? = null): Game {
            return Game(
                id = id,
                title = "Game Title",
                releaseDate = "3/31/2024",
                description = "desc",
                platforms = listOf("0"),
                genres = listOf("Action", "Adventure"),
                developer = "Developer",
                publisher = "Publisher",
                screenshots = listOf("", "", "", "", "", "", ""),
                cover = "",
                platformsData = listOf(
                    Platform("0", "PC", "PC"),
                    Platform("1", "PlayStation 5", "PS5")
                ),
                status = status
            )
        }
    }
}

