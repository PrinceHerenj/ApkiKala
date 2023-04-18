package dev.groupx.apkikala.ui.screen.profile_personal

import androidx.compose.runtime.Composable
import dev.groupx.apkikala.ui.navigation.NavigationDestination
import dev.groupx.apkikala.R.string as AppText

object ProfileNode: NavigationDestination {
    override val route = "ProfileScreen"
    override val titleRes = AppText.app_name
}

@Composable
fun PersonalProfileScreen() {

}