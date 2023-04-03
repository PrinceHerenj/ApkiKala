package dev.groupx.apkikala.model.service.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.groupx.apkikala.model.service.AccountService
import dev.groupx.apkikala.model.service.ConfigurationService
import dev.groupx.apkikala.model.service.LogService
import dev.groupx.apkikala.model.service.StorageService
import dev.groupx.apkikala.model.service.impl.AccountServiceImpl
import dev.groupx.apkikala.model.service.impl.ConfigurationServiceImpl
import dev.groupx.apkikala.model.service.impl.LogServiceImpl
import dev.groupx.apkikala.model.service.impl.StorageServiceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService

    @Binds
    abstract fun provideLogService(impl: LogServiceImpl): LogService

    @Binds
    abstract fun provideConfigurationService(impl: ConfigurationServiceImpl): ConfigurationService

    @Binds
    abstract fun provideStorageService(impl: StorageServiceImpl) : StorageService


}