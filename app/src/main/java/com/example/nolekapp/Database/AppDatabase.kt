package com.example.nolekapp.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.nolekapp.Database.Dao.TestResultatDao
import com.example.nolekapp.Database.Data.TestResultat

@Database(entities = [TestResultat::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val dao: TestResultatDao

    companion object {
        val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE TestResultat ADD COLUMN Reason TEXT NOT NULL DEFAULT ''")
                database.execSQL("ALTER TABLE TestResultat ADD COLUMN Status TEXT NOT NULL DEFAULT ''")
            }
        }
    }
}