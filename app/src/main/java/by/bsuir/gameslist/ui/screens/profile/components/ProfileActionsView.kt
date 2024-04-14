package by.bsuir.gameslist.ui.screens.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import by.bsuir.gameslist.ui.components.FilledTextButton

@Composable
fun ProfileActionsView(
    onEditProfile: () -> Unit,
    onSignOut: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ) {
        FilledTextButton(
            text = "Edit Profile",
            onClick = onEditProfile,
            modifier = Modifier.fillMaxWidth()
        )

        FilledTextButton(
            text = "Sign Out",
            onClick = onSignOut,
            modifier = Modifier.fillMaxWidth()
        )
    }
}