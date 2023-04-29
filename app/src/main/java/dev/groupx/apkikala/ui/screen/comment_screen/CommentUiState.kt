package dev.groupx.apkikala.ui.screen.comment_screen

import dev.groupx.apkikala.model.Comment

data class CommentUiState(
    val comment: String = "",
    val Comments: List<Comment> = emptyList(),
    val loading: Boolean = false
)