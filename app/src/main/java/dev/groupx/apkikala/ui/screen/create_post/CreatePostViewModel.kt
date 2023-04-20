package dev.groupx.apkikala.ui.screen.create_post

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.groupx.apkikala.model.service.LogService
import dev.groupx.apkikala.model.service.StorageService
import dev.groupx.apkikala.ui.screen.ApkiKalaViewModel
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(
    private val storageService: StorageService,
    logService: LogService,
) : ApkiKalaViewModel(logService) {
    var uiState = mutableStateOf(CreatePostUiState())
        private set

    private val title
        get() = uiState.value.title

    private val description
        get() = uiState.value.description


    fun onTitleChange(newValue: String) {
        uiState.value = uiState.value.copy(title = newValue)
    }

    fun onDescriptionChange(newValue: String) {
        uiState.value = uiState.value.copy(description = newValue)
    }

    fun tempAddImageToStorage(imageUri: Uri) {
        launchCatching {
            val setUrl = storageService.saveImageToTempStorageReturningUrl(imageUri)
            uiState.value = uiState.value.copy(isImageAdded = true, tempPostImageURL = setUrl)
        }
    }


}