package dev.groupx.apkikala.ui.screen.home

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.GetContent
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.groupx.apkikala.ui.common.composables.BasicButton
import dev.groupx.apkikala.ui.navigation.NavigationDestination
import dev.groupx.apkikala.R.string as AppText

object HomeNode : NavigationDestination {
    override val route = "HomeScreen"
    override val titleRes = AppText.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    restartApp: (String) -> Unit,
    openScreen: (String) -> Unit,
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState(initial = HomeUiState(false, ))

    val galleryLauncher = rememberLauncherForActivityResult(GetContent()) { imageUri ->
        imageUri?.let {
            viewModel.addImageToStorageAndFirestore(imageUri)
        }
    }

    var barSelectedIndex by remember { mutableStateOf(0) }


    Scaffold(
        floatingActionButton = {
//            FloatingActionButton(
//                onClick = { galleryLauncher.launch("image/*") },
//                shape = MaterialTheme.shapes.large
//            ) {
//                Icon(Icons.Filled.Add, contentDescription = "Add")
//            }
            ExtendedFloatingActionButton(
                onClick = { galleryLauncher.launch("image/*") },
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
                Text(text = "Add Post")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = barSelectedIndex == 0,
                    onClick = {
                        barSelectedIndex = 0
                    },
                    icon = { Icon(Icons.Filled.Home, contentDescription = null) }
                )
                NavigationBarItem(
                    selected = barSelectedIndex == 1,
                    onClick = {
                        barSelectedIndex = 1
                    },
                    icon = { Icon(Icons.Filled.Search, contentDescription = null) }
                )
                NavigationBarItem(
                    selected = barSelectedIndex == 2,
                    onClick = {
                        barSelectedIndex = 2
                    },
                    icon = { Icon(Icons.Filled.PlayArrow, contentDescription = null) }
                )
                NavigationBarItem(
                    selected = barSelectedIndex == 3,
                    onClick = {
                        barSelectedIndex = 3
                    },
                    icon = { Icon(Icons.Filled.Person, contentDescription = null) }
                )
            }
        }

    ) {
        Column(modifier = Modifier.padding(it)) {
            Post()

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
                        viewModel.onSignOutClick(restartApp)
                    }
                    BasicButton(AppText.delete_acc, modifier = Modifier.padding(start = 8.dp)) {
                        viewModel.onDeleteAccClick(openAndPopUp, uiState.currentUserId)
                    }
                }
            }
            Text(text = uiState.currentUserId, modifier = Modifier.padding(start = 8.dp))

        }
    }


}

