package dev.groupx.apkikala.model.service.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.groupx.apkikala.model.service.AccountService
import dev.groupx.apkikala.model.service.impl.AccountServiceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService
}