package com.rk.mynote.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rk.mynote.R
import com.rk.mynote.model.Notes
import com.rk.mynote.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    var title by mutableStateOf("")
    var content by mutableStateOf("")
    var pin by mutableIntStateOf(R.drawable.unpin)

    fun updateTitle(newTitle: String) {
        title = newTitle
    }

    fun updateContent(newContent: String) {
        content = newContent
    }

    fun updatePin(newPin: Int) {
        pin = newPin
    }

    fun getNotes(): LiveData<List<Notes>> {
        return repository.getNotes()
    }

    suspend fun getNoteById(id: Long): Notes {
        return repository.getNoteById(id)
    }

    fun addNoteAsync(note: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNotes(note)
        }
    }

    fun updateNoteAsync(note: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNotes(note)
        }
    }

    fun deleteNoteAsync(note: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNotes(note)
        }
    }
}