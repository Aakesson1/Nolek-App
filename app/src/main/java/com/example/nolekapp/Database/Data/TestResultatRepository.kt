package com.example.nolekapp.Database.Data



import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import io.realm.kotlin.Realm
import org.mongodb.kbson.ObjectId

interface TestResultatRepository {
    fun configureTheRealm()
    fun getData(): Flow<List<TestResultat>>
    fun filterData(name: String): Flow<List<TestResultat>>
    suspend fun insertTestResultat(testResultat: TestResultat)
    suspend fun updateTestResultat(testResultat: TestResultat)
    suspend fun deleteTestResultat(id: ObjectId)
}