package by.bsuir.gameslist.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.bsuir.gameslist.model.Response
import by.bsuir.gameslist.service.AuthenticationService
import by.bsuir.gameslist.service.UserService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userService: UserService,
    private val authenticationService: AuthenticationService
): ViewModel() {
    private val _user = authenticationService.getUserId()?.let { userId ->
        userService.getUser(userId)
    } ?: flowOf(Response.Error("User not found"))

    val user = _user.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Response.Loading
    )

    fun updateUser(editState: EditProfileState) {
        val user = user.value as? Response.Success ?: return
        val updatedUser = user.data.copy(
            name = editState.name,
            country = editState.country,
            gender = editState.gender,
            dateOfBirth = editState.dateOfBirth,
            bio = editState.bio,
            links = editState.links
        )
        viewModelScope.launch {
            userService.updateUser(updatedUser)
        }
    }

    fun signOut() {
        authenticationService.signOut()
    }
}