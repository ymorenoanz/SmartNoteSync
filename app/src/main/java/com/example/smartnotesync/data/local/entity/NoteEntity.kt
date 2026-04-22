package com.example.smartnotesync.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.smartnotesync.ui.states.SyncStatus

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)  val id: String, // Auto-incremented ID
    val content: String,
    val lastModified: Long,
    val syncStatus: SyncStatus,
    val isDeleted: Boolean = false
)
