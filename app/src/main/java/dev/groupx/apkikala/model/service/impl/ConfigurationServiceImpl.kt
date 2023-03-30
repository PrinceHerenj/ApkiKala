package dev.groupx.apkikala.model.service.impl

import com.google.firebase.ktx.Firebase
import dev.groupx.apkikala.R.xml as AppConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import dev.groupx.apkikala.BuildConfig
import dev.groupx.apkikala.model.service.ConfigurationService
import dev.groupx.apkikala.model.service.trace
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ConfigurationServiceImpl @Inject constructor() : ConfigurationService {
    private val remoteConfig
        get() = Firebase.remoteConfig

    init {
        if (BuildConfig.DEBUG) {
            val configSettings = remoteConfigSettings { minimumFetchIntervalInSeconds = 0 }
            remoteConfig.setConfigSettingsAsync(configSettings)
        }

        remoteConfig.setDefaultsAsync(AppConfig.remote_config_defaults)
    }

    override suspend fun fetchConfiguration(): Boolean =
        trace(FETCH_CONFIG_TRACE) { remoteConfig.fetchAndActivate().await() }

    companion object {
        private const val FETCH_CONFIG_TRACE = "fetchConfig"
    }

}