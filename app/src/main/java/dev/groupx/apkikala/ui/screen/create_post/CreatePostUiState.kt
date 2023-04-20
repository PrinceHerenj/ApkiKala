package dev.groupx.apkikala.ui.screen.create_post

data class CreatePostUiState(
    val isImageAdded: Boolean = false,
    var tempPostImageURL: String = "",
    val title: String = "",
    val description: String = ""
)