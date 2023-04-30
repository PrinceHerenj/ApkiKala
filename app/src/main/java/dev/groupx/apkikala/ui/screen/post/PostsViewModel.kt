package dev.groupx.apkikala.ui.screen.post

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.groupx.apkikala.R
import dev.groupx.apkikala.model.service.AccountService
import dev.groupx.apkikala.model.service.LogService
import dev.groupx.apkikala.model.service.StorageService
import dev.groupx.apkikala.ui.common.snackbar.SnackbarManager
import dev.groupx.apkikala.ui.screen.AccountUiState
import dev.groupx.apkikala.ui.screen.ApkiKalaViewModel
import dev.groupx.apkikala.ui.screen.comment_screen.CommonCommentNode
import dev.groupx.apkikala.ui.screen.common_profile.CommonProfileNode
import dev.groupx.apkikala.ui.screen.like_screen.CommonLikeNode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val accountService: AccountService,
    private val storageService: StorageService,
    logService: LogService,
) : ApkiKalaViewModel(logService) {

    var uiState = MutableStateFlow(PostsUiState())
        private set

    var accUiState = accountService.currentUser.map { AccountUiState(it.isAnonymous, it.id) }
        private set


    fun getPosts() {
        launchCatching {
                uiState.value = uiState.value.copy(loading = true)
                try {
                    val posts = withContext(Dispatchers.IO) {
                        storageService.getFeedPosts()
                    }
                    uiState.value = uiState.value.copy(loading = false, posts = posts)
                } catch (e: Exception) {
                    Log.d("here", e.toString())
                }
        }
    }
    fun onTopBarClick(openScreen: (String) -> Unit, user: String) {
        val newRoute = "${CommonProfileNode.route}/$user"
        openScreen(newRoute)
    }

    fun likePost(postId: String) {
        launchCatching {
            storageService.createLikeDocumentAndIncreasePostLikeCount(
                postId, accountService.currentUserId
            )
        }
    }

    fun dislikePost(postId: String) {
        launchCatching {
            storageService.removeLikeDocumentAndDecreasePostLikeCount(
                postId, accountService.currentUserId
            )
        }
    }

    fun showAnonymousError() = SnackbarManager.showMessage(R.string.Anonym_Error)
    fun onViewCommentsClick(openScreen: (String) -> Unit, postId: String) {
        val newRoute = "${CommonCommentNode.route}/${postId}"
        openScreen(newRoute)
    }

    fun onLikeClick(openScreen: (String) -> Unit, postId: String) {
        val newRoute = "${CommonLikeNode.route}/${postId}"
        openScreen(newRoute)
    }

    fun removePost(postId: String, openAndPopUp: (String, String) -> Unit, nodeRoute: String) {
        launchCatching {
            storageService.removePostStorageCollectionCommentsLikes(postId)
            openAndPopUp(nodeRoute, nodeRoute)
        }
    }

    fun report(postId: String) {
        launchCatching {
            storageService.reportPost(postId)
        }
    }


}