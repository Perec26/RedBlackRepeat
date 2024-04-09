package com.pepekprodakshn.redblackrepeat.di

import android.content.Context
import androidx.room.Room
import com.pepekprodakshn.redblackrepeat.main.RBRDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATABASE_NAME = "RBR_DATABASE"

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext app: Context,
    ) = Room.databaseBuilder(
        context = app,
        klass = RBRDatabase::class.java,
        name = DATABASE_NAME,
    ).build()

    @Singleton
    @Provides
    fun providePlayerDao(database: RBRDatabase) = database.playersDao()
}