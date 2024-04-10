package com.rk.mynote.repository

import androidx.lifecycle.LiveData
import com.rk.mynote.database.NoteDao
import com.rk.mynote.model.Notes
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao: NoteDao) {

    fun getNotes():LiveData<List<Notes>>
    {
        return noteDao.getNotes()
    }

    suspend fun getNoteById(id: Long): Notes {
        return noteDao.getNoteById(id)
    }

    suspend fun addNotes(notes: Notes){
        noteDao.addNote(notes)
    }

    suspend fun updateNotes(notes: Notes){
        noteDao.updateNote(notes)
    }

    suspend fun deleteNotes(notes: Notes){
        noteDao.deleteNote(notes)
    }
}