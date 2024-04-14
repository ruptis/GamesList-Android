package by.bsuir.gameslist.ui.screens.profile.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.bsuir.gameslist.model.User
import by.bsuir.gameslist.ui.screens.profile.EditProfileState
import java.time.LocalDate


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileView(
    user: User,
    onCancel: () -> Unit,
    onSave: (EditProfileState) -> Unit,
    modifier: Modifier = Modifier
) {
    var state by remember {
        mutableStateOf(
            EditProfileState(
                name = user.name,
                country = user.country ?: "",
                gender = user.gender,
                dateOfBirth = user.dateOfBirth,
                bio = user.bio ?: "",
                links = user.links ?: ""
            )
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "EditProfile")
                },
                navigationIcon = {
                    TextButton(onClick = onCancel) {
                        Text(text = "Cancel")
                    }
                },
                actions = {
                    TextButton(onClick = { onSave(state) }) {
                        Text(text = "Save")
                    }
                }
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.surfaceContainerHigh,
            shape = MaterialTheme.shapes.large
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ProfilePictureSection()
                PersonalInfoSection(
                    state = state,
                    onNameChanged = { state = state.copy(name = it) },
                    onCountryChanged = { state = state.copy(country = it) },
                    onLinksChanged = { state = state.copy(links = it) },
                    onGenderChanged = { state = state.copy(gender = it) },
                    onDateOfBirthChanged = { state = state.copy(dateOfBirth = it) }
                )
                AboutMeSection(
                    bio = state.bio,
                    onBioChanged = { state = state.copy(bio = it) }
                )
            }
        }
    }
}

@Composable
private fun ProfilePictureSection(
    modifier: Modifier = Modifier
) {
    FormSection(header = "Profile Picture", modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Outlined.AccountCircle,
                contentDescription = "Profile",
                modifier = Modifier
                    .width(120.dp)
                    .height(120.dp)
            )
        }
    }
}

@Composable
private fun PersonalInfoSection(
    state: EditProfileState,
    onNameChanged: (String) -> Unit,
    onCountryChanged: (String) -> Unit,
    onLinksChanged: (String) -> Unit,
    onGenderChanged: (User.Gender?) -> Unit,
    onDateOfBirthChanged: (LocalDate?) -> Unit,
    modifier: Modifier = Modifier
) {
    FormSection(header = "Personal Info", modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ProfileInputField(
                title = "Name",
                value = state.name,
                onValueChanged = onNameChanged,
                modifier = Modifier.fillMaxWidth()
            )
            ProfileInputField(
                title = "Country",
                value = state.country,
                onValueChanged = onCountryChanged,
                modifier = Modifier.fillMaxWidth()
            )
            ProfileInputField(
                title = "Links",
                value = state.links,
                onValueChanged = onLinksChanged,
                modifier = Modifier.fillMaxWidth()
            )
            GenderSelector(
                selectedGender = state.gender,
                onGenderSelected = onGenderChanged,
                modifier = Modifier.fillMaxWidth()
            )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                DateOfBirthSelector(
                    dateOfBirth = state.dateOfBirth,
                    onDateOfBirthSelected = onDateOfBirthChanged,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun ProfileInputField(
    title: String,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    FormRow(title = title, modifier = modifier) {
        BasicTextField(
            value = value,
            onValueChange = onValueChanged,
            singleLine = true,
            modifier = Modifier.weight(4f)
        )
    }
}

@Composable
private fun AboutMeSection(
    bio: String,
    onBioChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    FormSection(header = "About Me", modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BasicTextField(
                value = bio,
                onValueChange = onBioChanged,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "EditProfileView")
@Composable
private fun PreviewEditProfileView() {
    EditProfileView(
        user = User(
            id = "",
            name = "User",
            email = "user@gmail.com",
            country = "Belarus",
            dateOfBirth = LocalDate.now(),
            dateOfRegistration = LocalDate.now(),
            gender = User.Gender.Male,
            links = null,
            bio = "I am a user",
            gamesCount = 0,
            passedGamesCount = 0
        ),
        onCancel = {},
        onSave = {}
    )
}