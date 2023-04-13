package dev.groupx.apkikala.ui.screen.home

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.groupx.apkikala.R
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
    val uiState by viewModel.uiState.collectAsState(initial = HomeUiState(false, ""))

    val galleryLauncher = rememberLauncherForActivityResult(GetContent()) {imageUri ->
        imageUri?.let {
            viewModel.addImageToStorageAndFirestore(imageUri)
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { galleryLauncher.launch("image/*") },
            containerColor = MaterialTheme.colorScheme.secondary,
            shape = MaterialTheme.shapes.small) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
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
                BasicButton(AppText.sign_out, modifier = Modifier.padding()) {
                    viewModel.onSignOutClick(restartApp)
                }
            }
            Text(text = uiState.currentUserId)

        }
    }



}

