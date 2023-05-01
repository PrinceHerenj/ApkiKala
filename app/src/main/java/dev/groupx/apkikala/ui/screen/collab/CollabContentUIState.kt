package dev.groupx.apkikala.ui.screen.collab

import dev.groupx.apkikala.model.ChatRoom

data class CollabContentUIState(
    var chatRooms: List<ChatRoom> = listOf(
        ChatRoom("123", "", "Prince Herenj", "VhSI"),
        ChatRoom("123", "", "Prince Herenj", "VhSI")
    ),
    val loading: Boolean = false,
)