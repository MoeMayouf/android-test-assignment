package com.test.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ShacklesDao {

    @Query("SELECT * FROM shacklessearchentity ORDER BY date_added DESC")
    suspend fun getSearchQuery(): List<ShacklesSearchEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchQuery(entity: ShacklesSearchEntity)

    @Delete
    suspend fun deleteSearchQuery(entity: ShacklesSearchEntity)
}