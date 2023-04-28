package dev.groupx.apkikala.ui.screen.like_screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import dev.groupx.apkikala.R
import dev.groupx.apkikala.ui.navigation.NavigationDestination

object CommonLikeNode: NavigationDestination {
    override val route = "CommonLikeScreen"
    override val titleRes = R.string.app_name
}

@Composable
fun CommonLikeScreen(
    postId: String,
) {
    Text(text = "Like screen for $postId")
}