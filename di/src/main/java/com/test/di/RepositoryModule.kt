package com.test.di

import com.test.data.ShacklesRepositoryImpl
import com.test.domain.repository.ShacklesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindShackleRepository(repository: ShacklesRepositoryImpl): ShacklesRepository
}