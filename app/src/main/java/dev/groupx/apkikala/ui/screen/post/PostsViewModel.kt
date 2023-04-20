package dev.groupx.apkikala.ui.screen.post

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.groupx.apkikala.model.service.AccountService
import dev.groupx.apkikala.model.service.LogService
import dev.groupx.apkikala.model.service.StorageService
import dev.groupx.apkikala.ui.screen.ApkiKalaViewModel
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val storageService: StorageService,
    logService: LogService,
) : ApkiKalaViewModel(logService) {
    var uiState = mutableStateOf(PostsUiState())
        private set

    init {
        getPosts()
    }

    private fun getPosts() {
        launchCatching {
            uiState.value = uiState.value.copy(posts = storageService.getFeedPosts())
        }
    }

}