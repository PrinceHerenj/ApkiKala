package dev.groupx.apkikala.ui.screen.common_chat

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.groupx.apkikala.model.service.AccountService
import dev.groupx.apkikala.model.service.ChatService
import dev.groupx.apkikala.model.service.LogService
import dev.groupx.apkikala.ui.screen.AccountUiState
import dev.groupx.apkikala.ui.screen.ApkiKalaViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ChatRoomViewModel @Inject constructor(
    accountService: AccountService,
    private val chatService: ChatService,
    logService: LogService,
) : ApkiKalaViewModel(logService) {
    var uiState = MutableStateFlow(ChatRoomUiState())
        private set

    var accUiState = accountService.currentUser.map { AccountUiState(it.isAnonymous, it.id) }
        private set

    fun onBackClick(popUp: () -> Unit) { popUp() }

}