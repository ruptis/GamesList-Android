package by.bsuir.gameslist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun WelcomeView(
    onSignIn: () -> Unit,
    onSignUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Gray)
        )

        Text(text = "Games List")

        Button(onClick = onSignIn) {
            Text(text = "Sign In")
        }

        Button(onClick = onSignUp) {
            Text(text = "Sign Up")
        }
    }
}

@Preview(name = "WelcomeView")
@Composable
private fun PreviewWelcomeView() {
    WelcomeView(
        onSignIn = {},
        onSignUp = {}
    )
}