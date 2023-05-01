package dev.groupx.apkikala.model.service.impl

import com.google.firebase.firestore.FirebaseFirestore
import dev.groupx.apkikala.model.ChatRoom
import dev.groupx.apkikala.model.service.ChatService
import javax.inject.Inject

class ChatServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
): ChatService {
    fun getUserChatRooms(currentUserId: String): List<ChatRoom> {
        TODO("Not yet implemented")
    }
}