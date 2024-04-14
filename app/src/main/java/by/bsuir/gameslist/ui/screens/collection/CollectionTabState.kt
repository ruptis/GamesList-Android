package by.bsuir.gameslist.ui.screens.collection

import by.bsuir.gameslist.model.Game
import by.bsuir.gameslist.model.Response

data class CollectionTabState(
    val tab: CollectionTab,
    val games: Response<List<Game>>
)