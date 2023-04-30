package dev.groupx.apkikala.model

import com.google.firebase.Timestamp

data class Comment(
    val commentContent: String = "",
    val username: String = "",
    val createdAt: Timestamp = Timestamp(0, 0),
    val postId: String = ""
)
