package com.example.notesapplofcoding.data.database

import androidx.room.*
import com.example.notesapplofcoding.data.Note
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM Note ORDER BY noteId DESC")
    fun selectNotes(): Flow<List<Note>>

    @Query("SELECT * FROM Note Where noteTitle LIKE '%' || :searchQuery|| '%'")
    fun searchInNotesTitle(searchQuery: String):Flow<List<Note>>

    @Query("DELETE from Note")
    suspend fun deleteAllNotes()

}