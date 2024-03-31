package by.bsuir.gameslist.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AuthTextField(title: String, text: String, onTextChanged: (String) -> Unit) {
    TextField(
        value = text,
        onValueChange = onTextChanged,
        label = { Text(title) },
        modifier = Modifier.fillMaxWidth()
    )
}