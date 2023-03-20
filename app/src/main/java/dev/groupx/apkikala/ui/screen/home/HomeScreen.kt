package dev.groupx.apkikala.ui.screen.home

import androidx.compose.runtime.Composable
import dev.groupx.apkikala.R
import dev.groupx.apkikala.ui.navigation.NavigationDestination

object HomeNode: NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

