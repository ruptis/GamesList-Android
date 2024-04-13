package by.bsuir.gameslist.screens.collection

import androidx.annotation.DrawableRes
import by.bsuir.gameslist.R
import by.bsuir.gameslist.model.Status

enum class CollectionTab(
    val title: String,
    val status: Status,
    @DrawableRes val icon: Int,
    @DrawableRes val selectedIcon: Int
) {
    PlayingTab("Playing", Status.Playing, R.drawable.gamepad, R.drawable.gamepad_filled),
    PlanningTab("Planning", Status.Planning, R.drawable.clock, R.drawable.clock_filled),
    PassedTab("Passed", Status.Passed, R.drawable.check_circle, R.drawable.check_circle_filled),
    AbandonedTab("Abandoned", Status.Abandoned, R.drawable.stop_circle, R.drawable.stop_circle_filled);
}