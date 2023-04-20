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
    private var tempPostRef = ""


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

    override suspend fun saveImageUrlToFirestorePost(downloadUrl: Uri, userId: String) {
        firestore.collection(POSTS).document(newPostRef).set(
            mapOf(
                "URL" to downloadUrl,
                "CREATED_AT" to FieldValue.serverTimestamp(),
                "USER" to userId
            )
        ).await()
    }

    override suspend fun saveImageToTempStorageReturningUrl(imageUri: Uri): String {
        tempPostRef = firestore.collection(POSTS).document().id
        return storage.reference.child(IMGTEMP).child("$tempPostRef.jpg")
            .putFile(imageUri).await()
            .storage.downloadUrl.await()
            .toString()
    }

    override suspend fun removeTempImageAndReference() {
        storage.reference.child(IMGTEMP).child("$tempPostRef.jpg")
            .delete()
        firestore.collection(POSTS).document(tempPostRef)
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
        private const val IMGTEMP = "imagesTemp"
        private const val IMG = "imagesGlobal"
        private const val USERS = "users"
        private const val POSTS = "posts"

    }

}