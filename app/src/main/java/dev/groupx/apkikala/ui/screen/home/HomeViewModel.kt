package dev.groupx.apkikala.ui.screen.home

import android.net.Uri
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.groupx.apkikala.model.service.AccountService
import dev.groupx.apkikala.model.service.LogService
import dev.groupx.apkikala.model.service.StorageService
import dev.groupx.apkikala.ui.screen.AccountUiState
import dev.groupx.apkikala.ui.screen.ApkiKalaViewModel
import dev.groupx.apkikala.ui.screen.collab.CollabNode
import dev.groupx.apkikala.ui.screen.login.LoginNode
import dev.groupx.apkikala.ui.screen.profile_personal.PersonalProfileNode
import dev.groupx.apkikala.ui.screen.search.SearchNode
import dev.groupx.apkikala.ui.screen.sign_up.SignUpNode
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val accountService: AccountService,
    private val storageService: StorageService,
    logService: LogService
): ApkiKalaViewModel(logService) {
    var uiState = accountService.currentUser.map { AccountUiState(it.isAnonymous, it.id) }
        private set

    fun addImageToStorageAndFirestore(imageUri: Uri) {
        launchCatching {
            val downloadUrl = storageService.saveImageToStorageReturningUrl(imageUri)
            storageService.saveImageUrlToFirestorePost(downloadUrl, accountService.currentUserId)
        }
    }
    fun onSearchClick(openScreen: (String) -> Unit) = openScreen(SearchNode.route)
    fun onCollabClick(openScreen: (String) -> Unit) = openScreen(CollabNode.route)
    fun onPersonalProfileClick(openScreen: (String) -> Unit) = openScreen(PersonalProfileNode.route)

}