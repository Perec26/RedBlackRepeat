package com.pepekprodakshn.database.di

import android.content.Context
import androidx.room.Room
import com.pepekprodakshn.database.RBRDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATABASE_NAME = "RBR_DATABASE"

@Module
@InstallIn(SingletonComponent::class)
internal class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext app: Context,
    ) = Room.databaseBuilder(
        context = app,
        klass = RBRDatabase::class.java,
        name = DATABASE_NAME,
    ).build()
}