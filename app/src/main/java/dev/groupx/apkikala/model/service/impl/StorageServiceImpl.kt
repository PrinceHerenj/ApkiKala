package dev.groupx.apkikala.model.service.impl

import android.net.Uri
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import dev.groupx.apkikala.model.Post
import dev.groupx.apkikala.model.service.StorageService
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(
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

    override suspend fun saveImageToFirestorePost(downloadUrl: Uri, userId: String, title: String, description: String) {
        firestore.collection(POSTS).document(newPostRef).set(
            mapOf(
                "postImageUrl" to downloadUrl,
                "createdAt" to FieldValue.serverTimestamp(),
                "title" to title,
                "description" to description,
                "user" to userId,
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

        return querySnapshot.documents.mapNotNull { document ->
            var username = ""
            var profileImageUrl = ""
            val user = document.getString("user")
            if (user != null) {
                username = firestore.collection(USERS).document(user).get().await()
                    .getString("username").toString()
                profileImageUrl = firestore.collection(USERS).document(user).get().await()
                    .getString("profileImageUrl").toString()

            }
            document.toObject(Post::class.java)?.copy(postId = document.id, username = username, profileImageUrl = profileImageUrl)
        }
    }

    companion object {
        private const val IMG = "imagesGlobal"
        private const val USERS = "users"
        private const val POSTS = "posts"

    }

}