package com.rk.PasswordManager.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rk.PasswordManager.repository.AccountRepository
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class AccountViewModelFactory @Inject constructor(private val repository: AccountRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AccountViewModel(repository) as T
    }
}