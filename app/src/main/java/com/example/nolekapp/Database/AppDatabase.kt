package com.example.nolekapp.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nolekapp.Database.Dao.TestobjectDao
import com.example.nolekapp.Database.Data.TestObject

@Database(entities = [TestObject::class],
          version = 1
)

abstract class AppDatabase: RoomDatabase() {
    abstract val dao: TestobjectDao
}