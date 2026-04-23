package com.example.smartnotesync.data.repository

import com.example.smartnotesync.data.local.dao.NoteDao
import com.example.smartnotesync.data.local.entity.NoteEntity
import com.example.smartnotesync.data.mapper.toDomain
import com.example.smartnotesync.data.mapper.toEntity
import com.example.smartnotesync.domain.model.Note
import com.example.smartnotesync.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao): NoteRepository {
    override suspend fun insertNote(note: Note) {
        noteDao.insert(note.toEntity())
    }

    override fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes()
            .map {
                list -> list.map { it.toDomain() }
            }
    }
}