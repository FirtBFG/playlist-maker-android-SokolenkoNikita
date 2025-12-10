package com.example.myapplication

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.myapplication.data.db.DbProvider

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "search_history")

class PlaylistMakerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        DbProvider.init(this)
        instance = this
    }

    companion object {
        lateinit var instance: PlaylistMakerApp
            private set
    }
}

