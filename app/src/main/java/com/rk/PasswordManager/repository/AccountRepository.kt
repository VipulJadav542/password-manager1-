package com.rk.PasswordManager.repository

import androidx.lifecycle.LiveData
import com.rk.PasswordManager.database.AccountDao
import com.rk.PasswordManager.model.AccountData
import javax.inject.Inject

class AccountRepository @Inject constructor(private val acccountDao: AccountDao) {

    fun getAccount():LiveData<List<AccountData>>
    {
        return acccountDao.getAccount()
    }

    suspend fun getAccountById(id: Long): AccountData {
        return acccountDao.getAccountById(id)
    }

    suspend fun addAccount(accountData: AccountData){
        acccountDao.addAccount(accountData)
    }

    suspend fun updateAccount(accountData: AccountData){
        acccountDao.updateAccount(accountData)
    }

    suspend fun deleteAccount(accountData: AccountData){
        acccountDao.deleteAccount(accountData)
    }
}