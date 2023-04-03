package dev.groupx.apkikala.model.service

interface StorageService {
//    suspend fun saveImageToStorage()
//
//    suspend fun saveImageUrlToFirestorePost()

    suspend fun loadImageFromFirestore() {

    }
    
}

/*TODO
*
* 1. Create collections: users, posts, comments, likes ✅
* 2. Link posts to user, comments to post, likes as function from user to post ✅
* 3. Store image to storage, image url to firestore
* 4. Read ImageUrl from firestore to display on post
*
* */