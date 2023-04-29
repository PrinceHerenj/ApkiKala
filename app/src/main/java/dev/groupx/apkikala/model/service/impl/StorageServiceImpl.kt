package dev.groupx.apkikala.model.service.impl

import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import dev.groupx.apkikala.model.Comment
import dev.groupx.apkikala.model.Like
import dev.groupx.apkikala.model.Post
import dev.groupx.apkikala.model.SearchResult
import dev.groupx.apkikala.model.service.StorageService
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val storage: FirebaseStorage,
    private val firestore: FirebaseFirestore,
) : StorageService {

    private var newPostRef = ""

    override suspend fun saveImageToStorageReturningUrl(imageUri: Uri): Uri {
        newPostRef = firestore.collection(POSTS).document().id

        return storage.reference.child(IMG).child("$newPostRef.jpg")
            .putFile(imageUri).await()
            .storage.downloadUrl.await()
    }

    override suspend fun saveImageToFirestorePost(
        downloadUrl: Uri,
        userId: String,
        title: String,
        description: String,
    ) {
        firestore.collection(POSTS).document(newPostRef).set(
            mapOf(
                "postImageUrl" to downloadUrl,
                "createdAt" to FieldValue.serverTimestamp(),
                "title" to title,
                "description" to description,
                "user" to userId,
                "likes" to 0
            )
        ).await()
    }

    override suspend fun saveImageToFirestoreUser(downloadUrl: Uri, userId: String) {
        firestore.collection(USERS).document(userId).update(
            mapOf(
                "profileImageUrl" to downloadUrl
            )
        )
    }

    override suspend fun removeImage() {
        storage.reference.child(IMG).child("$newPostRef.jpg")
            .delete()
    }

    override suspend fun addUserToFirestoreOnSignUp(
        uid: String,
        username: String,
        address: String,
        bio: String,
    ) {
        firestore.collection(USERS).document(uid)
            .set(
                hashMapOf(
                    "username" to username,
                    "address" to address,
                    "bio" to bio,
                    "profileImageUrl" to ""
                )
            )
    }

    override suspend fun removeUserInfoFromFirestore(uid: String) {
        firestore.collection(USERS).document(uid)
            .delete()
    }

    override suspend fun getFeedPosts(): List<Post> {
        val query: Query = firestore.collection(POSTS)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .limit(20)

        val querySnapshot = query.get().await()

        val currentUserId = auth.currentUser?.uid

        return querySnapshot.documents.mapNotNull { document ->
            var username = ""
            var profileImageUrl = ""
            var likedByCurrentUser = false
            val user = document.getString("user")
            if (user != null) {
                username = firestore.collection(USERS).document(user).get().await()
                    .getString("username").toString()
                profileImageUrl = firestore.collection(USERS).document(user).get().await()
                    .getString("profileImageUrl").toString()
                likedByCurrentUser = isLikedByUser("${currentUserId}_${document.id}")
            }
            document.toObject(Post::class.java)?.copy(
                postId = document.id,
                username = username,
                profileImageUrl = profileImageUrl,
                likedByCurrentUser = likedByCurrentUser
            )
        }
    }

    override suspend fun getSearchResults(searchString: String): List<SearchResult> {
        val query = firestore.collectionGroup(USERS)
            .whereGreaterThanOrEqualTo("username", searchString)
            .whereLessThan("username", searchString + "\uf8ff")
            .limit(6)

        val querySnapshot = query.get().await()

        return querySnapshot.documents.mapNotNull { document ->
            document.toObject(SearchResult::class.java)?.copy(user = document.id)
        }

    }

    override suspend fun getLikes(postId: String): List<Like> {
        return firestore.collection(LIKES)
            .whereEqualTo("postId", postId).orderBy("createdAt")
            .get().await()
            .documents.mapNotNull { document ->
                val userId = document.getString("userId").toString()
                var username = "Deleted User"
                val userExist = firestore.collection(USERS).document(userId).get().await()
                if (userExist.exists()) username = userExist.getString("username").toString()
                document.toObject(Like::class.java)?.copy(username = username)
            }
    }

    override suspend fun getComments(postId: String): List<Comment> {
        return firestore.collection(COMMENTS)
            .whereEqualTo("postId", postId).orderBy("createdAt", Query.Direction.DESCENDING)
            .get().await()
            .documents.mapNotNull { document ->
                val userId = document.getString("userId").toString()
                var username = "Deleted User"
                val userExist = firestore.collection(USERS).document(userId).get().await()
                if (userExist.exists())
                {  username = userExist.getString("username").toString()
                    Log.d("Here", userId)
                }
                document.toObject(Comment::class.java)?.copy(username = username)
            }
    }

    override suspend fun createLikeDocumentAndIncreasePostLikeCount(postId: String, uid: String) {
        val documentRef = "${uid}_$postId"
        firestore.collection(LIKES).document(documentRef).set(
            hashMapOf(
                "userId" to uid,
                "postId" to postId
            )
        )
        val currentLikes = firestore.collection(POSTS).document(postId).get().await()
            .getLong("likes")
        val updatedLikes = currentLikes?.plus(1)

        firestore.collection(POSTS).document(postId).update(
            "likes", updatedLikes
        )
    }

    override suspend fun addCommentDocument(comment: String, postId: String, uid: String) {

        val newCommentRef = firestore.collection(POSTS).document().id
        firestore.collection(COMMENTS).document(newCommentRef).set(
            hashMapOf(
                "commentContent" to comment,
                "createdAt" to FieldValue.serverTimestamp(),
                "userId" to uid,
                "postId" to postId
            )
        )

    }

    override suspend fun isLikedByUser(documentRef: String): Boolean {
        val resultDeferred = CompletableDeferred<Boolean>()

        firestore.collection(LIKES).document(documentRef).get()
            .addOnSuccessListener {
                resultDeferred.complete(it.exists())
            }.addOnFailureListener { exception ->
                resultDeferred.completeExceptionally(exception)
            }

        return resultDeferred.await()

    }

    override suspend fun removeLikeDocumentAndDecreasePostLikeCount(postId: String, uid: String) {
        val documentRef = "${uid}_$postId"
        firestore.collection(LIKES).document(documentRef)
            .delete()
        val currentLikes = firestore.collection(POSTS).document(postId).get().await()
            .getLong("likes")
        val updatedLikes = currentLikes?.minus(1)

        firestore.collection(POSTS).document(postId).update(
            "likes", updatedLikes
        )
    }


    companion object {
        private const val IMG = "imagesGlobal"
        private const val USERS = "users"
        private const val POSTS = "posts"
        private const val LIKES = "likes"
        private const val COMMENTS = "comments"

    }

}