package com.rk.PasswordManager.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rk.PasswordManager.model.AccountData

@Dao
interface AccountDao {
    @Query("select * from AccountData order by AccountId desc")
    fun getAccount(): LiveData<List<AccountData>>

    @Query("SELECT * FROM AccountData WHERE AccountId = :id")
    suspend fun getAccountById(id: Long): AccountData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAccount(result: AccountData)

    @Update
    suspend fun updateAccount(result: AccountData)

    @Delete
    suspend fun deleteAccount(result:AccountData)

}