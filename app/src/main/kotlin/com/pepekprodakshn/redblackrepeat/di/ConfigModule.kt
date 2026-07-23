package com.pepekprodakshn.redblackrepeat.di

import com.pepekprodakshn.config.AppConfig
import com.pepekprodakshn.redblackrepeat.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ConfigModule {

    @Provides
    @Singleton
    fun provideAppConfig(): AppConfig = object : AppConfig {
        override val isDebug = BuildConfig.DEBUG
        override val version = BuildConfig.VERSION_NAME
    }
}
