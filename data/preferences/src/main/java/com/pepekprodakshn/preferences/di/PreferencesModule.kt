package com.pepekprodakshn.preferences.di

import android.content.Context
import com.pepekprodakshn.preferences.PreferencesDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class PreferencesModule {

    @Singleton
    @Provides
    fun providePreferencesDataSource(@ApplicationContext context: Context): PreferencesDataSource =
        PreferencesDataSource(context)
}
