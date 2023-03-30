package dev.groupx.apkikala.model.service

interface ConfigurationService {
    suspend fun fetchConfiguration(): Boolean
}