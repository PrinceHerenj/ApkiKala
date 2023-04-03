package dev.groupx.apkikala.model.service.impl

import com.google.firebase.storage.FirebaseStorage
import dev.groupx.apkikala.model.service.StorageService
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(private val storage: FirebaseStorage) : StorageService {
    override suspend fun loadImageFromFirestore() {

    }


    companion object {
        private const val EG_COL = "exampleCollection"
    }

}

/*
*
* 1.
*
* */