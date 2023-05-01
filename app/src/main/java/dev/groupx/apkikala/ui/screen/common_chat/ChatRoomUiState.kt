package dev.groupx.apkikala.ui.screen.common_chat

import dev.groupx.apkikala.model.Chat

data class ChatRoomUiState(
    val loading: Boolean = false,
    val chats: List<Chat> = emptyList()
)
