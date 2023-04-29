package dev.groupx.apkikala.ui.screen.comment_screen

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.groupx.apkikala.model.service.AccountService
import dev.groupx.apkikala.model.service.LogService
import dev.groupx.apkikala.model.service.StorageService
import dev.groupx.apkikala.ui.screen.ApkiKalaViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val storageService: StorageService,
    private val accountService: AccountService,
    logService: LogService,
) : ApkiKalaViewModel(logService) {

    var uiState = MutableStateFlow(CommentUiState())
        private set
    fun getComments(postId: String) {
        launchCatching {
            uiState.value = uiState.value.copy(loading = true)
            try {
                val comments = withContext(Dispatchers.IO) {
                    storageService.getComments(postId)
                }
                uiState.value = uiState.value.copy(loading = false, Comments = comments)
            } catch (e: Exception) {
                Log.d("In CommentViewMode", e.toString())
            }
        }
    }

    private val comment
        get() = uiState.value.comment

    fun onCommentChange(newValue: String) {
        uiState.value = uiState.value.copy(comment = newValue)
    }

    fun onBackClick(popUp: () -> Unit) {
        popUp()
    }

    fun addComment(comment: String, postId: String) {
        val userId = accountService.currentUserId
        launchCatching {
            storageService.addCommentDocument(comment, postId, userId)
        }
    }
}