package com.example.myapplication.data.db

import android.content.Context
import com.example.myapplication.data.database.AppDatabase

object DbProvider {
    private var databaseInstance: AppDatabase? = null

    fun init(context: Context) {
        if (databaseInstance == null) {
            databaseInstance = AppDatabase.getDatabase(context)
        }
    }

    val database: AppDatabase
        get() = databaseInstance
            ?: throw IllegalStateException("DbProvider must be initialized with init(context) first")
}
