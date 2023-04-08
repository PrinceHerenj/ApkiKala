package dev.groupx.apkikala.model.service.impl

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dev.groupx.apkikala.model.service.StorageService
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(private val storage: FirebaseStorage, private val firestore: FirebaseFirestore) : StorageService {

    override suspend fun saveImageToStorage(imageUri: Uri) {
        TODO("Not yet implemented")
//        return try {
//            storage.reference.child(IMGFOL).child("egFile.jpg")
//                .putFile()
//        }
    }

    override suspend fun saveImageUrlToFirestorePost(downloadUri: Uri) {
        TODO("Not yet implemented")
    }

    override suspend fun loadImageURLFromFirestore() : String {
        return firestore.collection(EG_COL).document(DOCID).get().await().getString("URL").toString()
    }

    companion object {
        // Firestore
        private const val DOCID = "OqITc7aQZSs3uL9E0qEF"
        private const val EG_COL = "exampleCollection"

        // Storage
        private const val IMGFOL = "imagesGlobal"
    }

}

/*
*
* 1.
*
* */