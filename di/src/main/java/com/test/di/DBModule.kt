package com.test.di

import android.content.Context
import androidx.room.Room
import com.test.database.AppDatabase
import com.test.database.AppDatabase.Companion.DATABASE_NAME
import com.test.database.ShacklesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideMovieDao(appDatabase: AppDatabase): ShacklesDao = appDatabase.searchQueryDao()

}