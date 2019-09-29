package com.pritam.githubmvvm.helpers

import android.content.Context
import com.pritam.githubmvvm.database.DatabaseCache
import com.pritam.githubmvvm.database.FactDatabase

/**
 * Dependency Injector for DatabaseCache
 */
object Injector {

    fun provideCache(context: Context): DatabaseCache {
        val database: FactDatabase = FactDatabase.getDatabaseInstance(context)
        return DatabaseCache(database)
    }
}
