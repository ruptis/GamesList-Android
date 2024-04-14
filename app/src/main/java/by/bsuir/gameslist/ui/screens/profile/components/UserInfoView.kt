package by.bsuir.gameslist.ui.screens.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import by.bsuir.gameslist.model.User
import by.bsuir.gameslist.ui.components.InfoRowView

@Composable
fun UserInfoView(
    user: User,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        InfoRowView(title = "Email", value = user.email)
        InfoRowView(title = "Country", value = user.country ?: "")
        InfoRowView(title = "Gender", value = user.gender?.name ?: "")
        InfoRowView(title = "Date of birth", value = user.dateOfBirth?.toString() ?: "")
        InfoRowView(title = "Date of registration", value = user.dateOfRegistration.toString())
        InfoRowView(title = "Links", value = user.links ?: "")
        InfoRowView(title = "Games in collection", value = user.gamesCount.toString())
        InfoRowView(title = "Games passed", value = user.passedGamesCount.toString())

        Text(
            text = "About me",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            text = user.bio ?: "",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}