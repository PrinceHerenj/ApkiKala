package dev.groupx.apkikala.model

data class Profile(
    val userId: String = "",
    val username: String = "",
    val profileImageUrl: String = "",
    val address: String = "",
    val bio: String = "",
    val followers: Long = 0,
    val following: Long = 0,
    val posts: Long = 0
)