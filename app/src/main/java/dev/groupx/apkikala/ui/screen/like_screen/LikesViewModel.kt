package dev.groupx.apkikala.ui.screen.like_screen

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.groupx.apkikala.model.service.LogService
import dev.groupx.apkikala.model.service.StorageService
import dev.groupx.apkikala.ui.screen.ApkiKalaViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LikesViewModel @Inject constructor(
    private val storageService: StorageService,
    logService: LogService
): ApkiKalaViewModel(logService) {
    var uiState = MutableStateFlow(LikeScreenUiState())
        private set

    fun getLikes(postId: String) {
        launchCatching {
            uiState.value = uiState.value.copy(loading = true)
            try {
                val likes = withContext(Dispatchers.IO) {
                    storageService.getLikes(postId)
                }
                uiState.value = uiState.value.copy(loading = false, likes = likes)
            } catch (e: Exception) {
                Log.d("Exception in Likes", e.toString())
            }
        }
    }

    fun onBackClick(popUp: () -> Unit) {
        popUp()
    }

}