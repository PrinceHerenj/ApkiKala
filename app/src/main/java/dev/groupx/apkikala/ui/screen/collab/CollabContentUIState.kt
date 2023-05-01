package dev.groupx.apkikala.ui.screen.collab

import dev.groupx.apkikala.model.ChatRoom

data class CollabContentUIState(
    var chatRooms: List<ChatRoom> = emptyList(),
    val loading: Boolean = false,
)