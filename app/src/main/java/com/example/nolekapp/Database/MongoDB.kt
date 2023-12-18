package com.example.nolekapp.Database

import android.util.Log
import com.example.nolekapp.Database.Constants.APP_ID
import com.example.nolekapp.Database.Data.TestResultat
import com.example.nolekapp.Database.Data.TestResultatRepository
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.log.LogLevel
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.ObjectId

object MongoDB: TestResultatRepository {
    val app = App.create(APP_ID)
    val user = app.currentUser
    private lateinit var realm: Realm

    init {
            configureTheRealm()
    }
    override fun configureTheRealm() {


        if (user != null) {
            val config = SyncConfiguration.Builder(user,
                setOf(TestResultat::class)
            )
                .initialSubscriptions{
                        sub -> add(query = sub.query<TestResultat>(query="ownerId==$0",user.id))
                }

                .log(LogLevel.ALL)

                .build()
            realm = Realm.open(config)
        }
    }
    override fun getData(): Flow<List<TestResultat>> {
            return realm.query<TestResultat>().asFlow().map { it.list }
    }

    override fun filterData(name: String): Flow<List<TestResultat>> {
        return realm.query<TestResultat>("name CONTAINS[c] $name").asFlow().map { it.list }
    }

    override suspend fun insertTestResultat(testResultat: TestResultat) {
 if (user != null){
     realm.write {
         try {
             copyToRealm(testResultat.apply { ownerId = user.id })
         }catch (e: Exception){
             Log.d("TestResultatRepository",e.message.toString())
         }
     }
 }
    }

    override suspend fun updateTestResultat(testResultat: TestResultat) {
        realm.write {
            val queriedTestResultat =
                query<TestResultat>(query = "_id == $0", testResultat._id).first().find()
            queriedTestResultat?.name = testResultat.name
        }
    }

    override suspend fun deleteTestResultat(id: ObjectId) {
        realm.write {
            val person = query<TestResultat>(query = "_id == $0", id).first().find()
            try {
                person?.let { delete(it) }
            } catch (e: Exception) {
                Log.d("TestResultatRepositoryImpl", "${e.message}")
            }
        }
    }
}