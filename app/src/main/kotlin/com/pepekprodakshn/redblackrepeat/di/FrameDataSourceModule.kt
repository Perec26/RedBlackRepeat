package com.pepekprodakshn.redblackrepeat.di

import com.pepekprodakshn.redblackrepeat.frame.data.FrameDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class FrameDataSourceModule {

    @ViewModelScoped
    @Provides
    fun provideFrameDataSource() = FrameDataSource()
}