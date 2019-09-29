package com.pritam.githubmvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pritam.githubmvvm.service.model.Fact

/**
 * Interface which provide methods for accessing Database Access Objects
 */
@Database(entities = [(Fact::class)], version = 1)
abstract class GitDatabase : RoomDatabase() {
    /**
     * Return the Fact database object, using which application access CRUD
     * @return the Database Access Object
     */
    abstract fun factDao(): GitDao

    companion object {
        private var databaseName = "git.db"
        private var INSTANCE: GitDatabase? = null

        // Get database instance is not null else create new
        fun getDatabaseInstance(context: Context): GitDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: Room.databaseBuilder(context,
                            GitDatabase::class.java,
                            databaseName)
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()
                }.also { INSTANCE = it }
    }
}