package dev.groupx.apkikala.ui.screen.create_post

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.groupx.apkikala.ui.common.composables.BasicButton
import dev.groupx.apkikala.ui.common.composables.CustomField
import dev.groupx.apkikala.ui.navigation.NavigationDestination
import dev.groupx.apkikala.ui.screen.post.GetPostImage
import dev.groupx.apkikala.ui.screen.post.ImageCommon
import dev.groupx.apkikala.R.string as AppText

object CreatePostNode : NavigationDestination {
    override val route = "CreatePostScreen"
    override val titleRes = AppText.app_name
}

@Composable
fun CreatePostScreen(
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CreatePostViewModel = hiltViewModel(),
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val uiState by viewModel.uiState

        val galleryLauncher =
            rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { imageUri ->
                imageUri?.let {
                    viewModel.tempAddImageToStorage(imageUri)
                }
            }

        if (!uiState.isImageAdded) {
            BasicButton(
                text = AppText.add_image,
                modifier = Modifier
                    .width(250.dp)
                    .padding(bottom = 8.dp)

            ) { galleryLauncher.launch("image/*") }
        } else {
            GetPostImage(postImageUrl = uiState.tempPostImageURL) { imageUrl ->
                ImageCommon(
                    imageUrl = imageUrl,
                    height = 400.dp,
                    width = 400.dp,
                    shape = RoundedCornerShape(4.dp),
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.padding(16.dp, 8.dp)
                )

            }
        }

        CustomField(
            text = "Add a title to your post",
            value = uiState.title,
            onNewValue = viewModel::onTitleChange,
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomField(
            text = "Add a description",
            value = uiState.description,
            onNewValue = viewModel::onDescriptionChange
        )

        if (uiState.isImageAdded) {
            BasicButton(
                text = AppText.add_post,
                modifier = Modifier
                    .width(250.dp)
                    .padding(vertical = 8.dp)
            ) {

            }
        }

    }
}