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
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.Timestamp
import dev.groupx.apkikala.model.Post
import dev.groupx.apkikala.ui.navigation.NavigationDestination
import dev.groupx.apkikala.ui.screen.AccountUiState
import dev.groupx.apkikala.ui.screen.post.PostItem
import dev.groupx.apkikala.R.string as AppText

object HomeNode : NavigationDestination {
    override val route = "HomeScreen"
    override val titleRes = AppText.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    openScreen: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState(initial = AccountUiState(false))

    val galleryLauncher = rememberLauncherForActivityResult(GetContent()) { imageUri ->
        imageUri?.let {
            viewModel.addImageToStorageAndFirestore(imageUri)
        }
    }

    Scaffold(
        floatingActionButton = {
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
                    selected = true,
                    onClick = { },
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
        Column(modifier = Modifier.padding(it)) {
            val tempPost = Post(
                title = "Sketch Up ⭐",
                description = "Created with 🥰",
                profileImageUrl = "https://firebasestorage.googleapis.com/v0/b/apki-kala.appspot.com/o/imagesGlobal%2F5dVVboSdA2JDcF17RAC2.jpg?alt=media&token=a1533f50-b63e-470d-ba03-ce07d9da252c",
                postImageUrl = "https://firebasestorage.googleapis.com/v0/b/apki-kala.appspot.com/o/imagesGlobal%2Fs3CuyQYSvDEVI0vm2Kyn.jpg?alt=media&token=a48d052f-d81e-48e1-8726-0e73417438a7",
                createdAt = Timestamp.now(),
                user = "C7F81ikBGQMN4m9boWNzbJ99JJ23",
                username = "Prince Herenj"
            )
            PostItem(post = tempPost)
        }
    }
}

