package com.pepekprodakshn.database.di

import com.pepekprodakshn.database.RBRDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DaoModule {

    @Singleton
    @Provides
    fun providePlayerDao(database: RBRDatabase) = database.playersDao()
}