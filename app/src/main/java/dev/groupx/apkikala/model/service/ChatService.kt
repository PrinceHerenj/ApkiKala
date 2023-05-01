package dev.groupx.apkikala.model.service

import dev.groupx.apkikala.model.ChatRoom

interface ChatService {
    suspend fun getUserChatRooms(currentUserId: String): List<ChatRoom>
    suspend fun checkChatRooms(currentUser: String, collabWithUserId: String): Boolean
    suspend fun createChatRooms(currentUser: String, collabWithUserId: String)


}