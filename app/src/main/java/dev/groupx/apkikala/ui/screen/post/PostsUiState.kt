package dev.groupx.apkikala.ui.screen.post

import dev.groupx.apkikala.model.Post

data class PostsUiState(
    var posts: List<Post> = emptyList()
)