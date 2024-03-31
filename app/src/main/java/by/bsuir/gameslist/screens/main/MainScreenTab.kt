package by.bsuir.gameslist.screens.main

import androidx.annotation.DrawableRes
import by.bsuir.gameslist.R

enum class MainScreenTab(
    val route: String,
    val title: String,
    @DrawableRes val icon: Int,
    @DrawableRes val selectedIcon: Int
) {
    HOME("home", "Home", R.drawable.home, R.drawable.home_filled),
    COLLECTION("collection", "Collection", R.drawable.collection, R.drawable.collection_filled),
    PROFILE("profile", "Profile", R.drawable.person, R.drawable.person_filled)
}