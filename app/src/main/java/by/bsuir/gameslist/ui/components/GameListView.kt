package by.bsuir.gameslist.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.bsuir.gameslist.model.Game

@Composable
fun GameListView(
    games: List<Game>,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    item: @Composable (Game) -> Unit
) {
    LazyColumn(
        state = listState,
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(games) { game ->
            item(game)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "GameListView", showBackground = true)
@Composable
private fun PreviewGameListView() {
    GameListView(games = (1..10).map { Game.mockGame() }) {
        Text(text = it.title)
    }
}