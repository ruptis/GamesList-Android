package by.bsuir.gameslist.screens.collection

import androidx.annotation.DrawableRes
import by.bsuir.gameslist.R
import by.bsuir.gameslist.model.Game

enum class CollectionTab(
    val title: String,
    val status: Game.Status,
    @DrawableRes val icon: Int,
    @DrawableRes val selectedIcon: Int
) {
    PLAYING("Playing", Game.Status.PLAYING, R.drawable.gamepad, R.drawable.gamepad_filled),
    PLANNING("Planning", Game.Status.PLANNING, R.drawable.clock, R.drawable.clock_filled),
    PASSED("Passed", Game.Status.PASSED, R.drawable.check_circle, R.drawable.check_circle_filled),
    ABANDONED("Abandoned", Game.Status.ABANDONED, R.drawable.stop_circle, R.drawable.stop_circle_filled);
}