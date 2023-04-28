package dev.groupx.apkikala.ui.screen.search

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import dev.groupx.apkikala.ui.navigation.NavigationDestination
import dev.groupx.apkikala.R.string as AppText

object SearchNode: NavigationDestination {
    override val route = "SearchScreen"
    override val titleRes = AppText.app_name
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    openScreen: (String) -> Unit,
    openAndPopUp: (String, String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    Scaffold(bottomBar = {
        NavigationBar {
            NavigationBarItem(
                selected = false,
                onClick = { viewModel.onHomeClick(openAndPopUp) },
                icon = { Icon(Icons.Filled.Home, contentDescription = null) }
            )
            NavigationBarItem(
                selected = true,
                onClick = { },
                icon = { Icon(Icons.Filled.Search, contentDescription = null) }
            )
            NavigationBarItem(
                selected = false,
                onClick = {
                    viewModel.onCollabClick(openScreen)
                },
                icon = { Icon(Icons.Filled.PlayArrow, contentDescription = null) }
            )
            NavigationBarItem(
                selected = false,
                onClick = {
                    viewModel.onPersonalProfileClick(openScreen)
                },
                icon = { Icon(Icons.Filled.Person, contentDescription = null) }
            )
        }
    }) {

    }
}