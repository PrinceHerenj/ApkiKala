package dev.groupx.apkikala.model.service.impl

import android.net.Uri
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
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


    override suspend fun getCurrentPost(postId: String): Post {
        return firestore.collection(POSTS).document(postId)
            .get().await().toObject<Post>()!!
    }

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



    override suspend fun removeImage() {
        storage.reference.child(IMG).child("$newPostRef.jpg")
            .delete()
    }


    // Not Required
//    override suspend fun loadImageURLFromFirestore(postRef: String): String {
//        return firestore.collection(POSTS).document(postRef).get().await().getString("URL")
//            .toString()
//
//    }
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
                    "bio" to bio
                )
            )
    }

    override suspend fun removeUserInfoFromFirestore(uid: String) {
        firestore.collection(USERS).document(uid)
            .delete()
    }

    companion object {
        private const val IMG = "imagesGlobal"
        private const val USERS = "users"
        private const val POSTS = "posts"

    }

}