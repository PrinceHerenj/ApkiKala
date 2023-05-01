package dev.groupx.apkikala.ui.screen.like_screen

import dev.groupx.apkikala.model.Like

data class LikeScreenUiState(
    var likes: List<Like> = emptyList(),
    val loading: Boolean = false
)