package dev.groupx.apkikala.ui.screen.common_profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import dev.groupx.apkikala.ui.navigation.NavigationDestination
import dev.groupx.apkikala.R.string as AppText

object CommonProfileNode : NavigationDestination {
    override val route = "CommonProfileScreen"
    override val titleRes = AppText.app_name
}

@Composable
fun CommonProfileScreen(
    uid: String
) {
    Text(text = uid)
}