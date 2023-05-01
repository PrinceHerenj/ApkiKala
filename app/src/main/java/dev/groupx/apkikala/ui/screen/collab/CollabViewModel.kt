package dev.groupx.apkikala.ui.screen.collab

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.groupx.apkikala.model.ChatRoom
import dev.groupx.apkikala.model.service.AccountService
import dev.groupx.apkikala.model.service.ChatService
import dev.groupx.apkikala.model.service.LogService
import dev.groupx.apkikala.ui.screen.AccountUiState
import dev.groupx.apkikala.ui.screen.ApkiKalaViewModel
import dev.groupx.apkikala.ui.screen.home.HomeNode
import dev.groupx.apkikala.ui.screen.profile_personal.PersonalProfileNode
import dev.groupx.apkikala.ui.screen.search.SearchNode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CollabViewModel @Inject constructor(
    private val chatService: ChatService,
    accountService: AccountService,
    logService: LogService,
) : ApkiKalaViewModel(logService) {
    var uiState = MutableStateFlow(CollabContentUIState())
        private set

    var accUiState = accountService.currentUser.map { AccountUiState(it.isAnonymous, it.id) }
        private set

    fun onHomeClick(openAndPopUp: (String, String) -> Unit) =
        openAndPopUp(HomeNode.route, PersonalProfileNode.route)

    fun onSearchClick(openScreen: (String) -> Unit) = openScreen(SearchNode.route)

    fun onPersonalProfileClick(openScreen: (String) -> Unit) = openScreen(PersonalProfileNode.route)
    fun onTopBarClick(openScreen: (String) -> Unit, node: String) {
        openScreen(node)
    }

    fun getCurrentUserChatRooms(currentUserId: String) {
        launchCatching {
            uiState.value = uiState.value.copy(loading = true)
            try {
                val userChatRooms = chatService.getUserChatRooms(currentUserId)
                if (userChatRooms.isNotEmpty())
                    uiState.value = uiState.value.copy(chatRooms = userChatRooms, loading = false)
                else
                    uiState.value = uiState.value.copy(
                        chatRooms = listOf(
                            ChatRoom("123", "", "Template Chat Room", "")
                        )
                    )
            } catch (e: Exception) {
                Log.d("InCollabViewModel", e.toString())
            }

        }
    }
}