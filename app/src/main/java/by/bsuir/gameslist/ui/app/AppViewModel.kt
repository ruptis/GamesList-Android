package by.bsuir.gameslist.ui.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.bsuir.gameslist.service.AuthenticationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    authenticationService: AuthenticationService
) : ViewModel() {
    var isAuthenticated: StateFlow<Boolean> = authenticationService.observeUserId()
        .map { it != null }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            authenticationService.getUserId() != null
        )
}