package dev.groupx.apkikala.model.service

import android.net.Uri

interface StorageService {
    suspend fun saveImageToStorage(imageUri: Uri)

    suspend fun saveImageUrlToFirestorePost(downloadUri: Uri)

    suspend fun loadImageURLFromFirestore(): String

}

/*TODO
*
* 1. Create collections: users, posts, comments, likes ✅
* 2. Link posts to user, comments to post, likes as function from user to post ✅
* 3. Read ImageUrl from firestore to display on post ✅
* 4. Store image to storage, image url to firestore
*
* */