package dev.groupx.apkikala.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
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
import dev.groupx.apkikala.ui.screen.post.HomePostScreen
import dev.groupx.apkikala.R.string as AppText

object HomeNode : NavigationDestination {
    override val route = "HomeScreen"
    override val titleRes = AppText.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    openScreen: (String) -> Unit,
    openAndPopUp: (String, String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState(initial = AccountUiState(false))
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
        floatingActionButton = {
            ExtendedFloatingActionButton(
                containerColor = MaterialTheme.colorScheme.secondary,
                onClick = {
                    if (!uiState.isAnonymousAccount) viewModel.onAddClick(openScreen)
                    else {
                        viewModel.showAnonymousError()
                    }
                },
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
                Text(text = "Add Post")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
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
        }

    ) {
        HomePostScreen(modifier = Modifier.padding(it), openScreen = openScreen)
    }
}

