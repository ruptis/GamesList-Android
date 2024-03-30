package by.bsuir.gameslist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import by.bsuir.gameslist.ui.theme.GamesListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GamesListTheme {
                GamesListApp()
            }
        }
    }
}