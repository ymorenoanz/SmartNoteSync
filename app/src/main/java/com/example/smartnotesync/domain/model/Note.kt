package com.example.smartnotesync.domain.model

import com.example.smartnotesync.ui.states.SyncStatus

data class Note(
    val id: String,
    val content: String,
    val lastModified: Long,
    val syncStatus: SyncStatus
)