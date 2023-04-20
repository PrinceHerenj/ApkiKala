package dev.groupx.apkikala.model

import com.google.firebase.Timestamp

data class Post(
    val profileImageUrl: String = "",
    val postImageUrl: String = "",
    val title: String = "",
    val description: String = "",
    val createdAt: Timestamp = Timestamp(0,0),
    val user: String = "",
    val username: String = ""
)
