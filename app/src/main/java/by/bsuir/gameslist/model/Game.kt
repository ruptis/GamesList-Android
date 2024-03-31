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
}

data class Platform(
    var id: String,
    var name: String,
    var abbreviation: String
)
