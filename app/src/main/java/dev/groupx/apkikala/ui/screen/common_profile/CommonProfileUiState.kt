package dev.groupx.apkikala.ui.screen.common_profile

import dev.groupx.apkikala.model.Post
import dev.groupx.apkikala.model.Profile

data class CommonProfileUiState(
    val isCurrentUserProfile: Boolean = false,
    val following: Boolean = false,
    val loading: Boolean = false,
    val profile: Profile = Profile(),
    val posts: List<Post> = emptyList()
)