package by.bsuir.gameslist.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import by.bsuir.gameslist.R
import by.bsuir.gameslist.model.Game
import by.bsuir.gameslist.ui.theme.GamesListTheme
import by.bsuir.gameslist.ui.theme.statusColors

@Composable
fun GameActionsView(
    status: Game.Status?,
    onStatusChange: (Game.Status) -> Unit, modifier:
    Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        ActionButton(
            onStatusChange,
            status,
            Game.Status.PLAYING,
            R.drawable.gamepad_filled,
            R.drawable.gamepad,
            MaterialTheme.statusColors.playing,
            modifier = Modifier.weight(0.33f)
        )
        ActionButton(
            onStatusChange,
            status,
            Game.Status.PLANNING,
            R.drawable.clock_filled,
            R.drawable.clock,
            MaterialTheme.statusColors.planning,
            modifier = Modifier.weight(0.33f)
        )
        ActionButton(
            onStatusChange,
            status,
            Game.Status.PASSED,
            R.drawable.check_circle_filled,
            R.drawable.check_circle,
            MaterialTheme.statusColors.passed,
            modifier = Modifier.weight(0.33f)
        )
        ActionButton(
            onStatusChange,
            status,
            Game.Status.ABANDONED,
            R.drawable.stop_circle_filled,
            R.drawable.stop_circle,
            MaterialTheme.statusColors.abandoned,
            modifier = Modifier.weight(0.33f)
        )
    }
}

@Composable
private fun ActionButton(
    onStatusChange: (Game.Status) -> Unit,
    status: Game.Status?,
    initialStatus: Game.Status,
    selectedIcon: Int,
    icon: Int,
    selectedColor: Color,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = { onStatusChange(initialStatus) },
        modifier = modifier
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(
                id = if (status == initialStatus) selectedIcon
                else icon
            ),
            contentDescription = "Playing",
            tint = if (status == initialStatus) selectedColor
            else MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Composable
@Preview(name = "GameActionsView", showBackground = true)
fun GameActionsViewPreview() {
    var status = remember { Game.Status.PLAYING }

    GamesListTheme {
        GameActionsView(
            status = status,
            onStatusChange = { status = it }
        )
    }
}