package by.bsuir.gameslist.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.bsuir.gameslist.model.Game
import by.bsuir.gameslist.model.Response
import by.bsuir.gameslist.model.Status
import by.bsuir.gameslist.service.AuthenticationService
import by.bsuir.gameslist.service.GameService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val gameService: GameService,
    private val authenticationService: AuthenticationService
) : ViewModel() {
    private var _games = authenticationService.getUserId()?.let { userId ->
        gameService.getGames(userId)
    } ?: emptyFlow()
    var games = _games.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Response.Loading
    )

    private var _selectedGame: MutableStateFlow<Game?> = MutableStateFlow(null)
    var selectedGame: StateFlow<Game?> = _selectedGame

    fun selectGame(game: Game) {
        _selectedGame.value = game
    }

    fun changeGameStatus(game: Game, status: Status) {
        val userId = authenticationService.getUserId() ?: return
        viewModelScope.launch {
            val newStatus = gameService.toggleStatus(game.id, status, userId)
            _selectedGame.value = game.copy(status = newStatus)
        }
    }
}