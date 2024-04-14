package by.bsuir.gameslist.ui.screens.profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import by.bsuir.gameslist.model.User
import by.bsuir.gameslist.ui.components.ResponseView
import by.bsuir.gameslist.ui.screens.profile.components.EditProfileView
import by.bsuir.gameslist.ui.screens.profile.components.ProfileActionsView
import by.bsuir.gameslist.ui.screens.profile.components.UserProfileView
import java.time.LocalDate

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val userResponse by viewModel.user.collectAsStateWithLifecycle()
    var showDialog by remember { mutableStateOf(false) }

    ResponseView(
        response = userResponse,
        modifier = modifier.fillMaxSize()
    ) {
        ProfileScreen(
            user = it,
            onEditProfile = { showDialog = true },
            onSignOut = viewModel::signOut,
            modifier = modifier.fillMaxSize()
        )
        if (showDialog) {
            Dialog(
                onDismissRequest = { showDialog = false },
                properties = DialogProperties(usePlatformDefaultWidth = false)
            ) {
                EditProfileView(
                    user = it,
                    onCancel = { showDialog = false },
                    onSave = { state ->
                        viewModel.updateUser(state)
                        showDialog = false
                    }
                )
            }
        }
    }
}

@Composable
fun ProfileScreen(
    user: User,
    onEditProfile: () -> Unit,
    onSignOut: () -> Unit,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState()
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .padding(32.dp)
            .verticalScroll(scrollState)
    ) {
        UserProfileView(
            user = user,
            modifier = modifier
                .padding(bottom = 48.dp)
        )

        ProfileActionsView(
            onEditProfile = onEditProfile,
            onSignOut = onSignOut
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "ProfileScreen", showBackground = true)
@Composable
private fun PreviewProfileScreen() {
    ProfileScreen(
        user = User(
            id = "1",
            name = "John Doe",
            email = "example@gmail.com",
            country = "USA",
            gender = User.Gender.Male,
            dateOfBirth = null,
            dateOfRegistration = LocalDate.now(),
            bio = "Hello, World!",
            links = "https://example.com",
            gamesCount = 0,
            passedGamesCount = 0
        ),
        onEditProfile = {},
        onSignOut = {},
        modifier = Modifier.fillMaxSize(),
    )
}