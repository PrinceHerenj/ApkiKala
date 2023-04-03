package dev.groupx.apkikala.model.service.impl

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dev.groupx.apkikala.model.service.StorageService
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(private val storage: FirebaseStorage, private val firestore: FirebaseFirestore) : StorageService {
    override suspend fun loadImageURLFromFirestore() : String {
        return firestore.collection(EG_COL).document(DOCID).get().await().getString("URL").toString()

    }


    companion object {
        private const val DOCID = "OqITc7aQZSs3uL9E0qEF"
        private const val EG_COL = "exampleCollection"
    }

}

/*
*
* 1.
*
* */