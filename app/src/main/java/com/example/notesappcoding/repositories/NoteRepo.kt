package com.example.notesapplofcoding.repositories

import com.example.notesapplofcoding.data.Note
import com.example.notesapplofcoding.data.database.NotesDatabase

class NoteRepo(
    notesDatabase: NotesDatabase
) {

    val noteDao = notesDatabase.noteDao()

    suspend fun upsertNote(note: Note) = noteDao.upsertNote(note)

    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)

    fun getNotes() = noteDao.selectNotes()

    fun searchNotes(searchQuery : String) = noteDao.searchInNotesTitle(searchQuery)

    suspend fun deleteAllNotes() = noteDao.deleteAllNotes()


}