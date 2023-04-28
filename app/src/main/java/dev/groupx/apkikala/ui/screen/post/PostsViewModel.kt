package dev.groupx.apkikala.ui.screen.post

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.groupx.apkikala.R
import dev.groupx.apkikala.model.service.AccountService
import dev.groupx.apkikala.model.service.LogService
import dev.groupx.apkikala.model.service.StorageService
import dev.groupx.apkikala.ui.common.snackbar.SnackbarManager
import dev.groupx.apkikala.ui.screen.AccountUiState
import dev.groupx.apkikala.ui.screen.ApkiKalaViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val accountService: AccountService,
    private val storageService: StorageService,
    logService: LogService,
) : ApkiKalaViewModel(logService) {
    var uiState = mutableStateOf(PostsUiState())
        private set

    var accUiState = accountService.currentUser.map { AccountUiState(it.isAnonymous, it.id) }
        private set

    init {
        getPosts()
    }

    fun getPosts() {
        launchCatching {
            uiState.value = uiState.value.copy(posts = storageService.getFeedPosts())
        }
    }

    fun likePost(postId: String) {
        launchCatching {
            storageService.createLikeDocumentAndIncreasePostLikeCount(
                postId,
                accountService.currentUserId
            )
            getPosts()
        }
    }

    fun dislikePost(postId: String) {
        launchCatching {
            storageService.removeLikeDocumentAndDecreasePostLikeCount(
                postId,
                accountService.currentUserId
            )
            getPosts()
        }
    }

    fun showAnonymousError() = SnackbarManager.showMessage(R.string.Anonym_AddPost)


}