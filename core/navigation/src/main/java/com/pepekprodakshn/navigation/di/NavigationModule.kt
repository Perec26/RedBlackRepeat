package com.pepekprodakshn.navigation.di

import android.content.Context
import com.pepekprodakshn.navigation.RBRNavController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class NavigationModule {

    @Singleton
    @Provides
    fun provideNavController(
        @ApplicationContext context: Context,
    ): RBRNavController = RBRNavController(context)
}