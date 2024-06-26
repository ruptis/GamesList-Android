package by.bsuir.gameslist.ui.screens.main

import androidx.annotation.DrawableRes
import by.bsuir.gameslist.R
import by.bsuir.gameslist.ui.screens.collection.navigation.COLLECTION_ROUTE
import by.bsuir.gameslist.ui.screens.home.navigation.HOME_ROUTE
import by.bsuir.gameslist.ui.screens.profile.navigation.PROFILE_ROUTE

enum class MainScreenTab(
    val route: String,
    val title: String,
    @DrawableRes val icon: Int,
    @DrawableRes val selectedIcon: Int
) {
    HOME(HOME_ROUTE, "Home", R.drawable.home, R.drawable.home_filled),
    COLLECTION(COLLECTION_ROUTE, "Collection", R.drawable.collection, R.drawable.collection_filled),
    PROFILE(PROFILE_ROUTE, "Profile", R.drawable.person, R.drawable.person_filled);

    companion object {
        fun fromRoute(route: String): MainScreenTab {
            return entries.first { it.route == route }
        }
    }
}