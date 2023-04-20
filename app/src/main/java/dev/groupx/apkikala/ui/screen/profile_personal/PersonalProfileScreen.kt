package dev.groupx.apkikala.ui.screen.profile_personal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.groupx.apkikala.ui.common.composables.BasicButton
import dev.groupx.apkikala.ui.navigation.NavigationDestination
import dev.groupx.apkikala.ui.screen.AccountUiState
import dev.groupx.apkikala.R.string as AppText

object PersonalProfileNode : NavigationDestination {
    override val route = "ProfileScreen"
    override val titleRes = AppText.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalProfileScreen(
    restartApp: (String) -> Unit,
    openScreen: (String) -> Unit,
    openAndPopUp: (String, String) -> Unit,
    viewModel: PersonalProfileViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState(initial = AccountUiState(false))

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        viewModel.onHomeClick(openScreen)
                    },
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
                    selected = true,
                    onClick = {  },
                    icon = { Icon(Icons.Filled.Person, contentDescription = null) }
                )
            }
        }
    ) {
        Column(Modifier.padding(it)) {
            Text(text = uiState.currentUserId, modifier = Modifier.padding(start = 8.dp))

            if (uiState.isAnonymousAccount) {
                Row {
                    BasicButton(AppText.sign_in, modifier = Modifier.padding(start = 8.dp)) {
                        viewModel.onLoginClick(openScreen)
                    }
                    BasicButton(AppText.sign_up, modifier = Modifier.padding(start = 8.dp)) {
                        viewModel.onSignUpClick(openScreen)
                    }

                }

            } else {
                Row {
                    BasicButton(AppText.sign_out, modifier = Modifier.padding(start = 8.dp)) {
                        viewModel.onSignOutClick(openScreen)
                    }
                    BasicButton(AppText.delete_acc, modifier = Modifier.padding(start = 8.dp)) {
                        viewModel.onDeleteAccClick(openAndPopUp, uiState.currentUserId)
                    }
                }
            }
        }
    }
}
