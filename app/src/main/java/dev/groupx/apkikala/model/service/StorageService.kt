package dev.groupx.apkikala.model.service

import android.net.Uri
import dev.groupx.apkikala.model.Post
import dev.groupx.apkikala.model.SearchResult

interface StorageService {

    suspend fun addUserToFirestoreOnSignUp(
        uid: String,
        username: String,
        address: String,
        bio: String,
    )

    suspend fun removeUserInfoFromFirestore(uid: String)

    suspend fun saveImageToStorageReturningUrl(imageUri: Uri): Uri

    suspend fun saveImageToFirestorePost(
        downloadUrl: Uri,
        userId: String,
        title: String,
        description: String,
    )

    suspend fun saveImageToFirestoreUser(downloadUrl: Uri, userId: String)

    suspend fun removeImage()
    suspend fun getFeedPosts(): List<Post>

    suspend fun createLikeDocumentAndIncreasePostLikeCount(postId: String, uid: String)
    suspend fun removeLikeDocumentAndDecreasePostLikeCount(postId: String, uid: String)
    suspend fun isLikedByUser(documentRef: String): Boolean
    suspend fun getSearchResults(searchString: String): List<SearchResult>
}
