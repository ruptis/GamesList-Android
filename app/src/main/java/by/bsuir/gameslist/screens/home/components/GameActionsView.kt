package by.bsuir.gameslist.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import by.bsuir.gameslist.R
import by.bsuir.gameslist.model.Game
import by.bsuir.gameslist.ui.theme.statusColors

@Composable
fun GameActionsView(
    status: Game.Status?,
    onStatusChange: (Game.Status) -> Unit, modifier:
    Modifier = Modifier
) {
    Row(
        modifier = modifier, horizontalArrangement = Arrangement.End
    ) {
        IconButton(onClick = { onStatusChange(Game.Status.PLAYING) }) {
            Icon(
                imageVector = ImageVector.vectorResource(
                    id = if (status == Game.Status.PLAYING) R.drawable.gamepad_filled
                    else R.drawable.gamepad
                ),
                contentDescription = "Playing",
                tint = if (status == Game.Status.PLAYING) MaterialTheme.statusColors.playing
                else MaterialTheme.colorScheme.onSurface,
                modifier = modifier.fillMaxSize(),
            )
        }
        IconButton(onClick = { onStatusChange(Game.Status.PLANNING) }) {
            Icon(
                imageVector = ImageVector.vectorResource(
                    id = if (status == Game.Status.PLANNING) R.drawable.clock_filled
                    else R.drawable.clock
                ),
                contentDescription = "Planning",
                tint = if (status == Game.Status.PLANNING) MaterialTheme.statusColors.planning
                else MaterialTheme.colorScheme.onSurface,
                modifier = modifier.fillMaxSize()
            )
        }
        IconButton(onClick = { onStatusChange(Game.Status.PASSED) }) {
            Icon(
                imageVector = ImageVector.vectorResource(
                    id = if (status == Game.Status.PASSED) R.drawable.check_circle_filled
                    else R.drawable.check_circle
                ),
                contentDescription = "Passed",
                tint = if (status == Game.Status.PASSED) MaterialTheme.statusColors.passed
                else MaterialTheme.colorScheme.onSurface,
                modifier = modifier.fillMaxSize()
            )
        }
        IconButton(onClick = { onStatusChange(Game.Status.ABANDONED) }) {
            Icon(
                imageVector = ImageVector.vectorResource(
                    id = if (status == Game.Status.ABANDONED) R.drawable.stop_circle_filled
                    else R.drawable.stop_circle
                ),
                contentDescription = "Abandoned",
                tint = if (status == Game.Status.ABANDONED) MaterialTheme.statusColors.abandoned
                else MaterialTheme.colorScheme.onSurface,
                modifier = modifier.fillMaxSize()
            )
        }
    }
}

@Composable
@Preview(name = "GameActionsView", showBackground = true)
fun GameActionsViewPreview() {
    var status = remember { Game.Status.PLAYING }

    GameActionsView(
        status = status,
        onStatusChange = { status = it }
    )
}