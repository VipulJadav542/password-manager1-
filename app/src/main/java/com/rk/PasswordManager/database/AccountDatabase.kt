package com.rk.PasswordManager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rk.PasswordManager.model.AccountData

@Database(entities = [AccountData::class], version = 1, exportSchema = false)
abstract class AccountDatabase : RoomDatabase() {
    abstract fun noteDao(): AccountDao

    companion object {
        @Volatile
        private var INSTANCE: AccountDatabase? = null

        fun getDatabase(context: Context): AccountDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AccountDatabase::class.java,
                    "Account_Database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
