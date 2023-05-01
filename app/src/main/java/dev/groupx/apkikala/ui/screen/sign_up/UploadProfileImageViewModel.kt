package dev.groupx.apkikala.ui.screen.sign_up

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.groupx.apkikala.model.service.AccountService
import dev.groupx.apkikala.model.service.LogService
import dev.groupx.apkikala.model.service.StorageService
import dev.groupx.apkikala.ui.screen.ApkiKalaViewModel
import javax.inject.Inject

@HiltViewModel
class UploadProfileImageViewModel @Inject constructor(
    private val accountService: AccountService,
    private val storageService: StorageService,
    logService: LogService
): ApkiKalaViewModel(logService){
    var uiState = mutableStateOf(UploadProfileImageUiState())
        private set

    private lateinit var setUrl: Uri

    fun addImageToStorage(imageUri: Uri) {
        launchCatching {
            setUrl = storageService.saveImageToStorageReturningUrl(imageUri)
            uiState.value = uiState.value.copy(isImageUrlAdded = true, profileImageUrl = setUrl.toString())
        }
    }

    fun onAddProfileImageClick(restartApp: (String) -> Unit, destRoute: String) {
        launchCatching {
            storageService.saveImageToFirestoreUser(setUrl, accountService.currentUserId)
            restartApp(destRoute)
        }
    }

    fun onCancel(popUp: () -> Unit, destRoute: String) {
        launchCatching {
            storageService.removeImage()
            if (destRoute == "${UploadProfileImageNode}/HomeScreen")
                accountService.deleteAccount(accountService.currentUserId)
            popUp()
        }
    }

    fun onBackClick(popUp: () -> Unit, destRoute: String) {
        launchCatching {
            Log.d("In UploadProfile", destRoute)
            if (destRoute == "${UploadProfileImageNode}/HomeScreen")
                accountService.deleteAccount(accountService.currentUserId)
            popUp()
        }
    }


}