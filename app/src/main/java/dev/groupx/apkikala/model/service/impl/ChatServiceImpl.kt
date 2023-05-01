package dev.groupx.apkikala.model.service.impl

import com.google.firebase.firestore.FirebaseFirestore
import dev.groupx.apkikala.model.ChatRoom
import dev.groupx.apkikala.model.service.ChatService
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ChatServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
) : ChatService {
    override suspend fun getUserChatRooms(currentUserId: String): List<ChatRoom> {
        return firestore.collection(CHATROOM)
            .whereEqualTo("sender", currentUserId).get().await()
            .documents.mapNotNull { document ->
                val userId = document.getString("receiver")!!
                val receiverDoc = firestore.collection("users").document(userId).get().await()
                val profileImageUrl = receiverDoc.getString("profileImageUrl")!!
                val username = receiverDoc.getString("username")!!

                document.toObject(ChatRoom::class.java)?.copy(
                    chatRoomId = document.id,
                    profileImageUrl = profileImageUrl,
                    username = username,
                    receiver = userId
                )
            }
    }

    override suspend fun checkChatRooms(currentUser: String, collabWithUserId: String): Boolean {
        return !firestore.collection(CHATROOM)
            .whereEqualTo("sender", currentUser)
            .get().await()
            .isEmpty
    }

    override suspend fun createChatRooms(currentUser: String, collabWithUserId: String) {
        val docRef1 = "${currentUser}_$collabWithUserId"
        val docRef2 = "${collabWithUserId}_$currentUser"
        firestore.collection(CHATROOM).document(docRef1).set(
            hashMapOf(
                "sender" to currentUser,
                "receiver" to collabWithUserId
            )
        )

        firestore.collection(CHATROOM).document(docRef2).set(
            hashMapOf(
                "sender" to collabWithUserId,
                "receiver" to currentUser
            )
        )
    }

    companion object {
        private const val CHATROOM = "chatRoom"
    }
}