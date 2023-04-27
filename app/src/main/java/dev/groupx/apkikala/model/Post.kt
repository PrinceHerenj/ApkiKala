package dev.groupx.apkikala.model

import com.google.firebase.Timestamp

data class Post(
    val postId: String = "",
    // fetched from firestore collection users
    val profileImageUrl: String = "",
    val username: String = "",
    // fetched from firestore collection posts
    val postImageUrl: String = "",
    val title: String = "",
    val description: String = "",
    val createdAt: Timestamp = Timestamp(0,0),
    val user: String = "",
    // for likes
    val likes: Int = 0,
    var likedByCurrentUser: Boolean = false
)

/* TODO
    * 1. Using currentUsedId and postId, create a document in likes collection ☑️
    * 2. Update postId like count ☑️
    * 3. update likedByCurrentUser
    * */