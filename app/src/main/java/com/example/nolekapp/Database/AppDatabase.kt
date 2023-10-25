package com.example.nolekapp.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.nolekapp.Database.Dao.TestResultatDao
import com.example.nolekapp.Database.Data.TestResultat

@Database(entities = [TestResultat::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract val dao: TestResultatDao

    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE TestObject RENAME TO TestResultat")
            }
        }
    }
}