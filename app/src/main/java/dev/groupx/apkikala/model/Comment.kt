package dev.groupx.apkikala.model

import com.google.firebase.Timestamp

data class Comment(
    val commentContent: String = "",
    val userId: String = "",
    val postId: String = "",
    val createdAt: Timestamp = Timestamp(0, 0),
)
