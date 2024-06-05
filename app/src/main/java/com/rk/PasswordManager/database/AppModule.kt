package com.rk.PasswordManager.database

import android.content.Context
import com.rk.PasswordManager.repository.AccountRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAccountDatabase(@ApplicationContext context: Context): AccountDatabase {
        return AccountDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideAccountDao(accountDatabase: AccountDatabase): AccountDao {
        return accountDatabase.noteDao()
    }

    @Provides
    @Singleton
    fun provideAccountRepository(accountDao: AccountDao): AccountRepository {
        return AccountRepository(accountDao)
    }
}



