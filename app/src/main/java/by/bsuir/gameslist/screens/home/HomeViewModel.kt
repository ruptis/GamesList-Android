package by.bsuir.gameslist.screens.home

import androidx.lifecycle.ViewModel
import by.bsuir.gameslist.model.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {
    private var _games : MutableStateFlow<List<Game>> = MutableStateFlow((1..10).map { Game.mockGame() })
    var games: StateFlow<List<Game>> = _games

    fun changeGameStatus(game: Game, status: Game.Status) {
        val updatedGames = _games.value.toMutableList()
        val index = updatedGames.indexOf(game)
        updatedGames[index] = game.copy(status = status)
        _games.value = updatedGames
    }

    fun onCardClick(game: Game) {
        // Handle card click
    }
}