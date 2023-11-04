package com.example.nolekapp.Database.Data

import kotlinx.coroutines.flow.Flow

interface TestResultatRepository {
    fun getData(): Flow<List<TestResultat>>
    fun filterData(name: String): Flow<List<TestResultat>>
    suspend fun insertTestResultat(testResultat: TestResultat)
    suspend fun updateTestResultat(testResultat: TestResultat)
    suspend fun deleteTestResultat(id: TestResultat)
}