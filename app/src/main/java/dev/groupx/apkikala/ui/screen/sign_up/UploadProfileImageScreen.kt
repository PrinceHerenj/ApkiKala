package dev.groupx.apkikala.ui.screen.sign_up

import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.groupx.apkikala.R
import dev.groupx.apkikala.ui.common.composables.BasicButton
import dev.groupx.apkikala.ui.navigation.NavigationDestination
import dev.groupx.apkikala.ui.screen.post.GetPostImage
import dev.groupx.apkikala.ui.screen.post.ImageCommon
import dev.groupx.apkikala.R.string as AppText

object UploadProfileImageNode : NavigationDestination {
    override val route = "UploadProfileImageScreen"
    override val titleRes = AppText.app_name
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadProfileImageScreen(
    destRoute: String,
    popUp: () -> Unit,
    restartApp: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UploadProfileImageViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.height(48.dp),
                title = { Text(text = "Upload Profile Image", Modifier.padding(top = 9.dp)) },
                navigationIcon = {
                    IconButton(onClick = {
                        if (uiState.isImageUrlAdded) {
                            viewModel.onCancel(popUp, destRoute)
                        } else viewModel.onBackClick(popUp, destRoute)
                    }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            val galleryLauncher =
                rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { imageUri ->
                    imageUri?.let {
                        viewModel.addImageToStorage(imageUri)
                    }
                }

            if (!uiState.isImageUrlAdded) {
                val canHandleBackButton by remember { mutableStateOf(true) }

                BasicButton(
                    text = AppText.upload_image,
                    modifier = Modifier
                        .width(250.dp)
                        .padding(bottom = 8.dp)

                ) { galleryLauncher.launch("image/*") }

                BackHandler(enabled = canHandleBackButton) {
                    viewModel.onBackClick(popUp, destRoute)
                }

            } else {
                GetPostImage(postImageUrl = uiState.profileImageUrl) { imageUrl ->
                    ImageCommon(
                        imageUrl = imageUrl,
                        height = 400.dp,
                        width = 400.dp,
                        shape = RoundedCornerShape(4.dp),
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.padding(16.dp, 8.dp)
                    )
                }
                val canHandleBackButton by remember { mutableStateOf(true) }

                BasicButton(
                    text = R.string.update_profile_image,
                    modifier = Modifier
                        .width(250.dp)
                        .padding(vertical = 8.dp)
                ) {
                    viewModel.onAddProfileImageClick(restartApp, destRoute)
                }

                BackHandler(enabled = canHandleBackButton) {
                    viewModel.onCancel(popUp, destRoute)
                }
            }

        }
    }
}