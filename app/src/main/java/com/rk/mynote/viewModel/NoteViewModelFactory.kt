package com.rk.mynote.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rk.mynote.repository.NoteRepository
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class NoteViewModelFactory @Inject constructor(private val repository: NoteRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(repository) as T
    }
}