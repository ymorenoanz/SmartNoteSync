package com.example.smartnotesync.domain.repository

import com.example.smartnotesync.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun insertNote(note: Note)
    fun getAllNotes(): Flow<List<Note>>
}