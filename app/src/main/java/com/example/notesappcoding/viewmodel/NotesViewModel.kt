package com.example.notesapplofcoding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapplofcoding.data.Note
import com.example.notesapplofcoding.repositories.NoteRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NotesViewModel(
    private val notesRepo: NoteRepo
) : ViewModel() {

    val notes = notesRepo.getNotes()

    val _searchNotes = MutableStateFlow<List<Note>>(emptyList())

    fun upsertNote(note: Note) = viewModelScope.launch {
        notesRepo.upsertNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        notesRepo.deleteNote(note)
    }

    fun deleteAllNotes() = viewModelScope.launch {
        notesRepo.deleteAllNotes()
    }

    fun searchNote(searchQuery: String) = viewModelScope.launch {
        notesRepo.searchNotes(searchQuery).collect {noteList ->
            _searchNotes.emit(noteList)
        }
    }

}