package dev.groupx.apkikala.model.service

import android.net.Uri

interface StorageService {
    suspend fun addUserToFirestoreOnSignUp(uid: String, username: String, address: String, bio: String)

    suspend fun removeUserInfoFromFirestore(uid: String)
    suspend fun saveImageToStorageReturningUrl(imageUri: Uri) : Uri

    suspend fun saveImageUrlToFirestorePost(downloadUrl: Uri)

    suspend fun loadImageURLFromFirestore(): String

    suspend fun getUniqueUsername(username: String): Boolean

}
