package com.example.smartnotesync.data.mapper

import com.example.smartnotesync.data.local.entity.NoteEntity
import com.example.smartnotesync.domain.model.Note
import com.example.smartnotesync.ui.states.SyncStatus

fun  NoteEntity.toDomain(): Note{
    return Note(
        id = id,
        content = content
    )
}

fun Note.toEntity(): NoteEntity{
    return NoteEntity(
        id = id,
        content = content,
        lastModified = System.currentTimeMillis(),
        syncStatus = SyncStatus.entries.random()
    )
}