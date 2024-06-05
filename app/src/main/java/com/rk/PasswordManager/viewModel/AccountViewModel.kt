package com.rk.PasswordManager.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rk.PasswordManager.model.AccountData
import com.rk.PasswordManager.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(private val repository: AccountRepository) : ViewModel() {
    var account by mutableStateOf("")
    var username by mutableStateOf("")
    var password by mutableStateOf("")

    fun updateAccount(newAccount: String) {
        account = newAccount
    }

    fun updateUserName(newUserName: String) {
        username = newUserName
    }

    fun updatePassword(newPassword: String) {
        password = newPassword
    }

    fun getAccount(): LiveData<List<AccountData>> {
        return repository.getAccount()
    }

    suspend fun getAccountById(id: Long): AccountData {
        return repository.getAccountById(id)
    }

    fun addAccountAsync(accountData: AccountData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAccount(accountData)
        }
    }

    fun updateAccountAsync(accountData: AccountData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateAccount(accountData)
        }
    }

    fun deleteAccountAsync(accountData: AccountData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAccount(accountData)
        }
    }
}