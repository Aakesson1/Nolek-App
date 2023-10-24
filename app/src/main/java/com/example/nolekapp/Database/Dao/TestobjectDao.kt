package com.example.nolekapp.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.nolekapp.Database.Data.TestObject

@Dao
interface TestobjectDao {
    @Upsert
    suspend fun upsertTestobject(testObject: TestObject)
    @Delete
    suspend fun deleteTestObject(testObject: TestObject)

    @Query("SELECT * FROM TestObject ORDER BY Serienumber_id ASC")
    fun getallTestObject(): LiveData<List<TestObject>>
}