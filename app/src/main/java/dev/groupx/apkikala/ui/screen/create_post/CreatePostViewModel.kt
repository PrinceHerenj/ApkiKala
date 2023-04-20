package dev.groupx.apkikala.ui.screen.create_post

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.groupx.apkikala.model.service.AccountService
import dev.groupx.apkikala.model.service.LogService
import dev.groupx.apkikala.model.service.StorageService
import dev.groupx.apkikala.ui.common.snackbar.SnackbarManager
import dev.groupx.apkikala.ui.screen.ApkiKalaViewModel
import dev.groupx.apkikala.ui.screen.home.HomeNode
import javax.inject.Inject
import dev.groupx.apkikala.R.string as AppText

@HiltViewModel
class CreatePostViewModel @Inject constructor(
    private val accountService: AccountService,
    private val storageService: StorageService,
    logService: LogService,
) : ApkiKalaViewModel(logService) {
    var uiState = mutableStateOf(CreatePostUiState())
        private set

    private val title
        get() = uiState.value.title

    private val description
        get() = uiState.value.description

    private lateinit var setUrl: Uri

    fun onTitleChange(newValue: String) {
        uiState.value = uiState.value.copy(title = newValue)
    }

    fun onDescriptionChange(newValue: String) {
        uiState.value = uiState.value.copy(description = newValue)
    }

    fun addImageToStorage(imageUri: Uri) {
        launchCatching {
            setUrl = storageService.saveImageToStorageReturningUrl(imageUri)
            uiState.value = uiState.value.copy(isImageUrlAdded = true, postImageURL = setUrl.toString())
        }
    }

    fun onAddPostClick(openAndPopUp: (String, String) -> Unit) {
        if (title.isBlank()) {
            SnackbarManager.showMessage(AppText.blank_title)
            return
        }

        if (description.isBlank()) {
            SnackbarManager.showMessage(AppText.blank_description)
            return
        }

        launchCatching {
            storageService.saveImageToFirestorePost(setUrl, accountService.currentUserId, uiState.value.title, uiState.value.description )
            openAndPopUp(HomeNode.route, CreatePostNode.route)
        }
    }

    fun onCancel(openAndPopUp: (String, String) -> Unit) {
        launchCatching {
            storageService.removeImage()
            openAndPopUp(HomeNode.route, CreatePostNode.route)
        }
    }

}