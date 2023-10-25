package com.example.nolekapp.Database.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.nolekapp.Database.Data.TestResultat
import kotlinx.coroutines.flow.Flow

@Dao
interface TestResultatDao {
    @Upsert
    suspend fun upsertTestresultat(testResultat: TestResultat)
    @Delete
    suspend fun deleteTestResultat(testResultat: TestResultat)

    @Query("SELECT * FROM TestResultat ORDER BY Serienumber_id ASC")
    fun getallTestResultat(): Flow<List<TestResultat>>
    @Query("SELECT * FROM TestResultat ORDER BY Name ASC")
    fun getallTestResultatOrderedByName(): Flow<List<TestResultat>>
}