package com.test.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ShacklesSearchEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun searchQueryDao(): SearchQueryDao

    companion object {
        const val DATABASE_NAME = "shackle_db"
    }
}