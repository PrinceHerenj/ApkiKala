package dev.groupx.apkikala.model.service

import android.net.Uri
import dev.groupx.apkikala.model.Comment
import dev.groupx.apkikala.model.Like
import dev.groupx.apkikala.model.Post
import dev.groupx.apkikala.model.Profile
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
    suspend fun getLikes(postId: String): List<Like>

    suspend fun getProfile(userId: String): Profile

    suspend fun addCommentDocument(comment: String, postId: String, uid: String)
    suspend fun getComments(postId: String): List<Comment>

    suspend fun addFollower(currentUserId: String, profileUserId: String, newFollowers: Long)
    suspend fun removeFollower(currentUserId: String, profileUserId: String, newFollowers: Long)
    suspend fun isFollowedBy(currentUserId: String, profileUserId: String): Boolean
    suspend fun getFeedPostsFiltered(userId: String): List<Post>
    suspend fun removePostStorageCollectionCommentsLikes(postId: String)
    fun reportPost(postId: String)
    suspend fun adminReportPost(postId: String)
    suspend fun getCurrentUserDetails(userId: String): List<String>
    suspend fun setUserDetails(userId: String, username: String, bio: String, address: String)
    suspend fun removePosts(userId: String)
}
