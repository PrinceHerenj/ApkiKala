package dev.groupx.apkikala.ui.screen.common_chat

import dev.groupx.apkikala.model.Chat

data class ChatRoomUiState(
    val loading: Boolean = false,
    val chats: List<Chat> = listOf(
        Chat("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin viverra urna eget mauris vestibulum, ac fringilla ante fringilla. Curabitur sodales elit a elit dictum hendrerit. Sed eget lectus justo. Cras eget nunc euismod, posuere urna vel, pharetra nulla. Nam eleifend lorem vel mauris lobortis ullamcorper.", "VhSTIPYloJZ3aJpLD9FCQ5eUlrk2", receiver = "5678"),
        Chat("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin viverra urna eget mauris vestibulum, ac fringilla ante fringilla. Curabitur sodales elit a elit dictum hendrerit. Sed eget lectus justo. Cras eget nunc euismod, posuere urna vel, pharetra nulla. Nam eleifend lorem vel mauris lobortis ullamcorper.", "5678", receiver = "VhSTIPYloJZ3aJpLD9FCQ5eUlrk2"),
    )
)
