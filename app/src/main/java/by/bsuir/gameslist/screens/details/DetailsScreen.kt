package by.bsuir.gameslist.screens.details

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DetailsScreen(
    id: String,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(text = "DetailsScreen $id")
    }
}

@Preview(name = "DetailsScreen")
@Composable
private fun PreviewDetailsScreen() {
    DetailsScreen("1")
}