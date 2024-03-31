package by.bsuir.gameslist.app

import androidx.lifecycle.ViewModel
import by.bsuir.gameslist.service.AuthenticationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    authenticationService: AuthenticationService
) : ViewModel() {
    var isAuthenticated: StateFlow<Boolean> = authenticationService.isAuthenticated
}