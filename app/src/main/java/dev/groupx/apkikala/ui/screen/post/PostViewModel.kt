package dev.groupx.apkikala.ui.screen.post

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.groupx.apkikala.model.service.LogService
import dev.groupx.apkikala.model.service.StorageService
import dev.groupx.apkikala.ui.screen.ApkiKalaViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val storageService: StorageService,
    logService: LogService
) :ApkiKalaViewModel(logService) {
    var uiState = mutableStateOf(PostUiState())
        private set

    init {
        getPosts()
    }

    private fun getPosts() {
//        launchCatching {
//            uiState.value = uiState.value.copy(post = storageService.getCurrentPost("5dVVboSdA2JDcF17RAC2"))
//        }
    }

}