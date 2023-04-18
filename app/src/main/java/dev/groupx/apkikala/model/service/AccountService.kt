package dev.groupx.apkikala.model.service

import dev.groupx.apkikala.model.User
import kotlinx.coroutines.flow.Flow

interface AccountService {
    val currentUserId: String
    val hasUser: Boolean

    val currentUser: Flow<User>

    suspend fun authenticate(email: String, password: String)
    suspend fun sendRecoveryEmail(email: String)
    suspend fun createAnonymousAccount()
    suspend fun linkAccount(email: String, password: String, username: String, address: String, bio: String)
    suspend fun deleteAccount(id: String)
    suspend fun signOut()
}