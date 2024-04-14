package by.bsuir.gameslist.ui.screens.collection

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
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val gameService: GameService,
    private val authenticationService: AuthenticationService
): ViewModel() {
    private var _games = authenticationService.getUserId()?.let { userId ->
        gameService.getGames(userId)
    } ?: flowOf(Response.Error("User not found"))

    var games = _games.map { games ->
        CollectionTab.entries.map { tab ->
            when (games) {
                is Response.Success -> {
                    val filteredGames = games.data.filter { it.status == tab.status }
                    CollectionTabState(tab, Response.Success(filteredGames))
                }
                is Response.Loading -> CollectionTabState(tab, Response.Loading)
                is Response.Error -> CollectionTabState(tab, Response.Error(games.message))
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CollectionTab.entries.map { tab ->
            CollectionTabState(tab, Response.Loading)
        }
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