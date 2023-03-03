package com.example.effectivemobiletest.ui.models.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.effectivemobiletest.ui.models.local.interfaces.UserDao

@Database(
    version = 2,
    entities = [
        UserDbEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

}