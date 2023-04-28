package dev.groupx.apkikala.ui.screen.comment_screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import dev.groupx.apkikala.R
import dev.groupx.apkikala.ui.navigation.NavigationDestination

object CommonCommentNode: NavigationDestination {
    override val route = "CommonCommentScreen"
    override val titleRes = R.string.app_name
}

@Composable
fun CommonCommentScreen(
    postId: String,

) {
    Text(text = "For $postId")
}