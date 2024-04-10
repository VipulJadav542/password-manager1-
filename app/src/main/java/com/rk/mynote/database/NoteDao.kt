package com.rk.mynote.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rk.mynote.model.Notes

@Dao
interface NoteDao {
    @Query("select * from Notes order by noteId desc")
    fun getNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM notes WHERE noteId = :id")
    suspend fun getNoteById(id: Long): Notes

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(result: Notes)

    @Update
    suspend fun updateNote(result: Notes)

    @Delete
    suspend fun deleteNote(result:Notes)

}