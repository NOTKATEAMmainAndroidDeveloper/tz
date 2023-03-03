package com.example.effectivemobiletest.ui.module

import android.content.Context
import androidx.room.Room
import com.example.effectivemobiletest.ui.models.local.AppDatabase
import com.example.effectivemobiletest.ui.repositories.LocalDbRep

class RoomModule {
    private lateinit var applicationContext: Context

    lateinit var appDatabase: AppDatabase
    lateinit var userRep: LocalDbRep

    fun init(context: Context): RoomModule {
        applicationContext = context

        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "database.db"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

        userRep = LocalDbRep(appDatabase.getUserDao())

        return this@RoomModule
    }
}