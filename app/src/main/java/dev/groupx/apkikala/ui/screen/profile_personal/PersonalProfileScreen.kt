package dev.groupx.apkikala.ui.screen.profile_personal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
    popUp: () -> Unit,
    openScreen: (String) -> Unit,
    openAndPopUp: (String, String) -> Unit,
    clearAndNavigate: (String) -> Unit,
    viewModel: PersonalProfileViewModel = hiltViewModel(),
) {
    val accUiState by viewModel.accUiState.collectAsState(initial = AccountUiState(false))
    val userId = accUiState.currentUserId
    Scaffold(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.secondary),
        topBar = {
            var expanded by remember { mutableStateOf(false) }
            CenterAlignedTopAppBar(modifier = Modifier.height(48.dp),
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = { viewModel.onBackClick(popUp) }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            Icons.Filled.MoreVert,
                            contentDescription = "Options",
                            tint = Color.Black,
                            modifier = Modifier.size(25.dp)
                        )
                        DropdownMenu(expanded = expanded,
                            onDismissRequest = { expanded = false }) {
                            if (accUiState.isAnonymousAccount) {
                                DropdownMenuItem(text = { Text("Login") },
                                    onClick = { viewModel.onLoginClick(openScreen) })
                                DropdownMenuItem(text = { Text("SignUp") },
                                    onClick = { viewModel.onSignUpClick(openScreen) })
                            } else {
                                DropdownMenuItem(text = { Text("Edit Profile") },
                                    onClick = { /*viewModel.onEditClick(openScreen)*/ })
                                DropdownMenuItem(text = { Text("Logout") },
                                    onClick = { viewModel.onSignOutClick(clearAndNavigate) })
                                DropdownMenuItem(text = { Text("Delete") }, onClick = {
                                    viewModel.onDeleteAccClick(
                                        clearAndNavigate, accUiState.currentUserId
                                    )
                                })
                            }

                        }

                    }
                })
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(selected = false, onClick = {
                    viewModel.onHomeClick(openAndPopUp)
                }, icon = { Icon(Icons.Filled.Home, contentDescription = null) })
                NavigationBarItem(selected = false, onClick = {
                    viewModel.onSearchClick(openScreen)
                }, icon = { Icon(Icons.Filled.Search, contentDescription = null) })
                NavigationBarItem(selected = false, onClick = {
                    viewModel.onCollabClick(openScreen)
                }, icon = { Icon(Icons.Filled.PlayArrow, contentDescription = null) })
                NavigationBarItem(selected = true,
                    onClick = { },
                    icon = { Icon(Icons.Filled.Person, contentDescription = null) })
            }
        },
    ) {
        ProfileSection(openScreen, openAndPopUp, Modifier.padding(it), userId)
    }
}
