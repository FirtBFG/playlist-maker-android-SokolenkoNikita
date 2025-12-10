package com.example.myapplication.data.db

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

object DbProvider {
    private val appScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    val database: DatabaseMock = DatabaseMock(scope = appScope)
}

