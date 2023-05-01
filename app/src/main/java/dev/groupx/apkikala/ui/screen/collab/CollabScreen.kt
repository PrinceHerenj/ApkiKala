package dev.groupx.apkikala.ui.screen.collab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.groupx.apkikala.R
import dev.groupx.apkikala.ui.navigation.NavigationDestination
import dev.groupx.apkikala.ui.screen.AccountUiState
import dev.groupx.apkikala.R.string as AppText


object CollabNode : NavigationDestination {
    override val route = "CollabScreen"
    override val titleRes = AppText.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollabScreen(
    openScreen: (String) -> Unit,
    openAndPopUp: (String, String) -> Unit,
    viewModel: CollabViewModel = hiltViewModel()
) {
    val accUiState by viewModel.accUiState.collectAsState(initial = AccountUiState(false))
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.height(48.dp),
                title = { Icon(
                    painter = painterResource(id = R.drawable.icons8_son_goku),
                    contentDescription = "Back",
                    tint = Color.Black,
                    modifier = Modifier
                        .padding(vertical = 2.dp)
                ) }
            )
        },
        bottomBar = {
        NavigationBar {
            NavigationBarItem(
                selected = false,
                onClick = { viewModel.onHomeClick(openAndPopUp) },
                icon = { Icon(Icons.Filled.Home, contentDescription = null) }
            )
            NavigationBarItem(
                selected = false,
                onClick = {
                    viewModel.onSearchClick(openScreen)
                },
                icon = { Icon(Icons.Filled.Search, contentDescription = null) }
            )
            NavigationBarItem(
                selected = true,
                onClick = {  },
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
        Box(Modifier.padding(it)) {
            CollabContent(openScreen, accUiState.currentUserId)
        }
    }
}