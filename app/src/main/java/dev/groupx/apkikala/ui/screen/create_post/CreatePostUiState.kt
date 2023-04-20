package dev.groupx.apkikala.ui.screen.create_post

data class CreatePostUiState(
    val isImageUrlAdded: Boolean = false,
    var postImageURL: String = "",
    val title: String = "",
    val description: String = ""
)