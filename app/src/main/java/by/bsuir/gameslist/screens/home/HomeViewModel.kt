package by.bsuir.gameslist.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.bsuir.gameslist.model.Game
import by.bsuir.gameslist.model.Response
import by.bsuir.gameslist.service.GameService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val gameService: GameService,
) : ViewModel() {
    private var _games = gameService.getGames().stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        Response.Loading
    )
    var games: StateFlow<Response<List<Game>>> = _games

    private var _selectedGame : MutableStateFlow<Game?> = MutableStateFlow(null)
    var selectedGame: StateFlow<Game?> = _selectedGame

    fun selectGame(game: Game) {
        _selectedGame.value = game
    }

    fun changeGameStatus(game: Game, status: Game.Status) {
        Log.d("HomeViewModel", "changeGameStatus: $game, $status")
        viewModelScope.launch {
            val newGame = gameService.toggleStatus(game.id, status)
            _selectedGame.value = newGame
        }
    }
}