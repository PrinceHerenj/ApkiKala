package dev.groupx.apkikala.model.service

import android.net.Uri

interface StorageService {
    suspend fun saveImageToStorageReturningUrl(imageUri: Uri) : Uri

    suspend fun saveImageUrlToFirestorePost(downloadUrl: Uri)

    suspend fun loadImageURLFromFirestore(): String

    suspend fun getUniqueUsername(username: String): Boolean

}

/*TODO
*
* 1. Create collections: users, posts, comments, likes ✅
* 2. Link posts to user, comments to post, likes as function from user to post ✅
* 3. Read ImageUrl from firestore to display on post ✅
* 4. Store image to storage, image url to firestore
*
* */