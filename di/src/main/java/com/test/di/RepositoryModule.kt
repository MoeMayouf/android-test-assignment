package com.test.di

import com.test.data.ShacklesRepositoryImpl
import com.test.database.ShacklesDao
import com.test.domain.repository.ShacklesRepository
import com.test.network.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Provides
    @Singleton
    fun provideShacklesRepository(
        apiService: ApiService,
        shacklesDao: ShacklesDao
    ): ShacklesRepository = ShacklesRepositoryImpl(apiService, shacklesDao)
}
