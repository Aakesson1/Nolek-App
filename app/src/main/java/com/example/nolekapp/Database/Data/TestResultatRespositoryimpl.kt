package com.example.nolekapp.Database.Data

import android.util.Log
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
/*
class TestResultatRespositoryimpl(val realm: Realm) : TestResultatRepository {
    override fun getData(): Flow<List<TestResultat>>{
        return realm.query<TestResultat>().asFlow().map { it.list }
    }

    override fun filterData(name: String): Flow<List<TestResultat>> {
        return realm.query<TestResultat>(query = "name CONTAINS[c] $0", name).asFlow().map { it.list }
    }

    override suspend fun insertTestResultat(testResultat: TestResultat) {
        realm.write { copyToRealm(testResultat) }
    }

    override suspend fun updateTestResultat(testResultat: TestResultat) {
        realm.write {
            val queriedTestResultat = query<TestResultat>("_id == $0", testResultat.serienumberId).first().find()
            queriedTestResultat?.name = testResultat.name
        }
    }

    override suspend fun deleteTestResultat(testResultat: TestResultat) {
        realm.write {
            val queriedTestResultat = query<TestResultat>("serienumberId == $0", testResultat.serienumberId).first().find()
            queriedTestResultat?.let { delete(it) }
        }
    }
}


 */