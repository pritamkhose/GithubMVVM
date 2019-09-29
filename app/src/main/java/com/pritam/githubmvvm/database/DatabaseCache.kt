package com.pritam.githubmvvm.database

import android.util.Log

import com.pritam.githubmvvm.service.model.Fact
import com.pritam.githubmvvm.utils.Constants

/**
 * * Database cache which will call respective dao methods
 */
class DatabaseCache(appDatabase: GitDatabase) {

    private val factDao = appDatabase.factDao()

    // insert facts in to database
    fun insert(facts: MutableList<Fact>) {
        Log.d(Constants.APP_TAG, "Inserting: ${facts.size} fact")
        factDao.insert(facts)
    }

    // get all facts from database
    fun getAllFacts(): MutableList<Fact> {
        return factDao.getAllFacts()
    }

    // delete all facts from database
    fun deleteAllFacts() {

        factDao.deleteAllFacts()

    }

}
