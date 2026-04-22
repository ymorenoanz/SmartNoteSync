package com.example.smartnotesync.data.repository

import com.example.smartnotesync.data.local.dao.NoteDao
import com.example.smartnotesync.domain.model.Note
import com.example.smartnotesync.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao): NoteRepository {
    override suspend fun insertNote(note: Note) {
        noteDao.insert(note)
    }

    override fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes()
    }
}