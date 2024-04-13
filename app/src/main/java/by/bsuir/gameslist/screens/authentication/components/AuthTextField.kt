package by.bsuir.gameslist.screens.authentication.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

enum class TextFieldType {
    Email, Password, Text
}

@Composable
fun AuthTextField(
    title: String,
    text: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    type: TextFieldType = TextFieldType.Text,
) {
    TextField(
        value = text,
        onValueChange = onTextChanged,
        label = { Text(title) },
        singleLine = true,
        visualTransformation = if (type == TextFieldType.Password)
            PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = when (type) {
                TextFieldType.Email -> KeyboardType.Email
                TextFieldType.Password -> KeyboardType.Password
                else -> KeyboardType.Text
            }
        ),
        modifier = modifier.fillMaxWidth()
    )
}

@Preview
@Composable
fun AuthTextFieldPreview() {
    AuthTextField("Title", "Text", onTextChanged = {})
}