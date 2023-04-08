package dev.groupx.apkikala.ui.screen.home

import androidx.compose.runtime.Composable
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.groupx.apkikala.R
import dev.groupx.apkikala.ui.navigation.NavigationDestination

object HomeNode : NavigationDestination {
    override val route = "HomeScreen"
    override val titleRes = R.string.app_name
}

@Composable
fun HomeScreen(
//    openAndPopUp: (String, String) -> Unit,
) {
    Post()
}

