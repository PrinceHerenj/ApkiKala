package dev.groupx.apkikala.ui.screen.search

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import dev.groupx.apkikala.ui.navigation.NavigationDestination
import dev.groupx.apkikala.R.string as AppText

object SearchNode: NavigationDestination {
    override val route = "SearchScreen"
    override val titleRes = AppText.app_name
}


@Composable
fun SearchScreen() {
    Box(contentAlignment = Alignment.Center) {
        Text(text = "Search")
    }
}